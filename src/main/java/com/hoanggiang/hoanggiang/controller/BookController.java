package com.hoanggiang.hoanggiang.controller;

import com.hoanggiang.hoanggiang.domain.Book;
import com.hoanggiang.hoanggiang.exception.ValidException;
import com.hoanggiang.hoanggiang.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "API quản lý sách")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Lấy tất cả sách", description = "Trả về danh sách tất cả sách trong cơ sở dữ liệu.")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    @Operation(summary = "Tạo sách mới", description = "Thêm một sách mới vào cơ sở dữ liệu.")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) throws ValidException {
        if(this.bookService.existsByName(book.getName())){
            throw new ValidException("Book name is exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.createBook(book));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy sách theo ID", description = "Trả về chi tiết sách dựa vào ID.")
    public ResponseEntity<Book> getBookById(
            @PathVariable("id") String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật sách", description = "Cập nhật thông tin sách dựa trên ID.")
    public ResponseEntity<Book> updateBook(@PathVariable("id") String id, @Valid @RequestBody Book book) throws ValidException {
        if(this.bookService.existsByName(book.getName())){
            throw new ValidException("Book name is exists");
        }
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa sách", description = "Xóa sách khỏi cơ sở dữ liệu dựa trên ID.")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") String id) {
        this.bookService.deleteBook(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm sách", description = "Tìm kiếm sách theo name, price, category, author.")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String author) {
        return ResponseEntity.ok(bookService.searchBooks(name, price, category, author));
    }
}
