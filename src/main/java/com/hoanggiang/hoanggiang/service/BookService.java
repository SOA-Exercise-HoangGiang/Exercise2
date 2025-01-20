package com.hoanggiang.hoanggiang.service;

import com.hoanggiang.hoanggiang.domain.Book;
import com.hoanggiang.hoanggiang.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setName(updatedBook.getName());
        book.setPrice(updatedBook.getPrice());
        book.setCategory(updatedBook.getCategory());
        book.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(book);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
    public boolean existsByName(String name){
        return this.bookRepository.existsByName(name);
    }
}
