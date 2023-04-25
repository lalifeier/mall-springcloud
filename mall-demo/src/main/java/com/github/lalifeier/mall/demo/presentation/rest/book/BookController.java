package com.github.lalifeier.mall.demo.presentation.rest.book;

import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.ListBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.UpdateBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.response.BookResponse;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.response.ListBookResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  @GetMapping("")
  public ListBookResponse listBook(@Validated @RequestBody ListBookRequest request) {
    return null;
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable String id) {
    return null;
  }

  @PostMapping("")
  public BookResponse createBook(@Validated @RequestBody CreateBookRequest request) {
    return null;
  }

  @PutMapping("")
  public BookResponse updateBook(@Validated @RequestBody UpdateBookRequest request) {
    return null;
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable String id) {
  }
}
