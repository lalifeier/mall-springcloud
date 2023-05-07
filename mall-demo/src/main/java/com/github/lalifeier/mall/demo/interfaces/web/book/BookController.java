package com.github.lalifeier.mall.demo.interfaces.web.book;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.applicaiton.service.BookApplicationService;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.request.BookPageRequest;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.response.BookResponse;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  private final BookApplicationService bookApplicationService;

  public BookController(BookApplicationService bookApplicationService) {
    this.bookApplicationService = bookApplicationService;
  }

  @PostMapping("")
  public void createBook(@Validated @RequestBody CreateBookRequest request) {
    this.bookApplicationService.createBook(request);
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable Long id) {
    return this.bookApplicationService.getBookById(id);
  }

  @PutMapping("/{id}")
  public void updateBook(@Validated @RequestBody UpdateBookRequest request) {
    this.bookApplicationService.updateBook(request);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    this.bookApplicationService.deleteBook(id);
  }

  @GetMapping("")
  public PageList<BookResponse> getBooks(@Validated @RequestParam BookPageRequest request) {
    return this.bookApplicationService.getBooks(request.getPageNum(), request.getPageSize());
  }

  // @GetMapping("/list")
  // public List<BookResponse> getAllBooks(@Validated @RequestParam
  // ListBookRequest request) {
  // return this.bookApplicationService.getAllBooks();
  // }
}
