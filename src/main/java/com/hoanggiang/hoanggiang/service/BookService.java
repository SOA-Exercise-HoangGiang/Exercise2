package com.hoanggiang.hoanggiang.service;

import com.hoanggiang.hoanggiang.domain.Book;
import com.hoanggiang.hoanggiang.repository.BookCustomRepository;
import com.hoanggiang.hoanggiang.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookCustomRepository bookCustomRepository;

    public BookService(BookRepository bookRepository,BookCustomRepository bookCustomRepository) {
        this.bookRepository = bookRepository;
        this.bookCustomRepository = bookCustomRepository;
    }

    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    public Book updateBook(String id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setName(updatedBook.getName());
        book.setPrice(updatedBook.getPrice());
        book.setCategory(updatedBook.getCategory());
        book.setAuthor(updatedBook.getAuthor());
        return this.bookRepository.save(book);
    }

    public void deleteBook(String id) {
        this.bookRepository.deleteById(id);
    }

    public boolean existsByName(String name){
        return this.bookRepository.existsByName(name);
    }

    public List<Book> searchBooks(String name, Double price, String category, String author) {
        return this.bookCustomRepository.searchBooks(name, price, category, author);
    }
}
