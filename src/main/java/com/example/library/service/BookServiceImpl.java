package com.example.library.service;

import com.example.library.dto.IsakhanKhangeldiBookDTO;
import com.example.library.dto.IsakhanKhangeldiCreateBookRequest;
import com.example.library.entity.IsakhanKhangeldiBook;
import com.example.library.entity.IsakhanKhangeldiAuthor;
import com.example.library.exception.IsakhanKhangeldiBookNotFoundException;
import com.example.library.exception.IsakhanKhangeldiAuthorNotFoundException;
import com.example.library.repository.IsakhanKhangeldiBookRepository;
import com.example.library.repository.IsakhanKhangeldiAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final IsakhanKhangeldiBookRepository bookRepository;
    private final IsakhanKhangeldiAuthorRepository authorRepository;  // ← добавили

    @Override
    public IsakhanKhangeldiBookDTO createBook(IsakhanKhangeldiCreateBookRequest request) {
        // Находим автора по ID
        IsakhanKhangeldiAuthor author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IsakhanKhangeldiAuthorNotFoundException("Author not found with id: " + request.getAuthorId()));

        IsakhanKhangeldiBook book = new IsakhanKhangeldiBook();
        book.setTitle(request.getTitle());
        book.setAuthor(author);  // ← теперь передаем объект Author
        book.setIsbn(request.getIsbn());
        book.setPublicationYear(request.getPublicationYear());
        book.setAvailable(true);

        IsakhanKhangeldiBook saved = bookRepository.save(book);
        return convertToDTO(saved);
    }

    @Override
    public IsakhanKhangeldiBookDTO getBookById(Long id) {
        IsakhanKhangeldiBook book = bookRepository.findById(id)
                .orElseThrow(() -> new IsakhanKhangeldiBookNotFoundException("Book not found with id: " + id));
        return convertToDTO(book);
    }

    @Override
    public Page<IsakhanKhangeldiBookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public Page<IsakhanKhangeldiBookDTO> searchBooks(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return getAllBooks(pageable);
        }
        return bookRepository.searchBooks(search, pageable).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public IsakhanKhangeldiBookDTO updateBook(Long id, IsakhanKhangeldiCreateBookRequest request) {
        IsakhanKhangeldiBook book = bookRepository.findById(id)
                .orElseThrow(() -> new IsakhanKhangeldiBookNotFoundException("Book not found with id: " + id));


        if (request.getAuthorId() != null) {
            IsakhanKhangeldiAuthor author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new IsakhanKhangeldiAuthorNotFoundException("Author not found with id: " + request.getAuthorId()));
            book.setAuthor(author);
        }

        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublicationYear(request.getPublicationYear());

        IsakhanKhangeldiBook updated = bookRepository.save(book);
        return convertToDTO(updated);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IsakhanKhangeldiBookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private IsakhanKhangeldiBookDTO convertToDTO(IsakhanKhangeldiBook book) {
        return new IsakhanKhangeldiBookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),  // ← берем имя из объекта Author
                book.getIsbn(),
                book.getPublicationYear(),
                book.getAvailable()
        );
    }
}