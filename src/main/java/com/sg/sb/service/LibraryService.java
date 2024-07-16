package com.sg.sb.service;

import com.sg.sb.model.Author;
import com.sg.sb.model.Book;
import com.sg.sb.model.dto.AuthorDTO;
import com.sg.sb.model.dto.BookDTO;
import com.sg.sb.model.request.AddAuthorRequest;
import com.sg.sb.model.request.AddBookRequest;
import com.sg.sb.repository.AuthorRepository;
import com.sg.sb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void addBook(AddBookRequest request){
        Author author = authorRepository.findById(Long.valueOf(request.getAuthorId())).orElseThrow(() -> new RuntimeException("Author not found"));
        Book newBook = new Book();
        newBook.setName(request.getName());
        newBook.setAuthor(author);

        author.getBooks().add(newBook);

        authorRepository.save(author);
    }

    @CacheEvict(value = "authors", allEntries = true)
    public void addAuthor(AddAuthorRequest request){
        Author newAuthor = new Author();
        newAuthor.setName(request.getName());

        authorRepository.save(newAuthor);
    }

    @Cacheable(value = "authors", key = "#root.methodName")
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AuthorDTO convertToDto(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBooks(author.getBooks().stream().map(this::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    private BookDTO convertToDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
        return dto;
    }
}
