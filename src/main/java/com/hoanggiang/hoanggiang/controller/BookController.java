package com.hoanggiang.hoanggiang.controller;

import com.hoanggiang.hoanggiang.domain.Book;
import com.hoanggiang.hoanggiang.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @Operation(summary = "Tạo sách mới", description = "Thêm một sách mới vào cơ sở dữ liệu.")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy sách theo ID", description = "Trả về chi tiết sách dựa vào ID.")
    public Book getBookById(
            @Parameter(description = "ID của sách cần tìm", required = true)
            @PathVariable("id") String id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật sách", description = "Cập nhật thông tin sách dựa trên ID.")
    public Book updateBook(@PathVariable("id") String id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa sách", description = "Xóa sách khỏi cơ sở dữ liệu dựa trên ID.")
    public void deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
    }
}
