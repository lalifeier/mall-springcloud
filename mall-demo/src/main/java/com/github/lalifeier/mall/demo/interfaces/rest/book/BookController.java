package com.github.lalifeier.mall.demo.interfaces.rest.book;

import java.util.List;

import com.github.lalifeier.mall.demo.interfaces.rest.book.converter.BookConverter;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.request.BookPageRequest;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.response.BookResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.common.result.PageResult;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.UpdateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.service.BookApplicationService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  private final BookApplicationService bookApplicationService;

  private final BookConverter bookConverter;

  public BookController(BookApplicationService bookApplicationService) {
    this.bookApplicationService = bookApplicationService;
    this.bookConverter = BookConverter.INSTANCE;
  }

  @PostMapping("")
  public void createBook(@Validated @RequestBody CreateBookRequest createBookRequest) {
    CreateBookBO createBookBO = bookConverter.toDTO(createBookRequest);
    this.bookApplicationService.createBook(createBookBO);
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable Long id) {
    BookBO bookBO = this.bookApplicationService.getBookById(id);
    return this.bookConverter.toVO(bookBO);
  }

  @PutMapping("/{id}")
  public void updateBook(@PathVariable Long id, @Validated @RequestBody UpdateBookRequest updateBookRequest) {
    UpdateBookBO updateBookBO = bookConverter.toDTO(updateBookRequest);
    updateBookBO.setId(id);
    this.bookApplicationService.updateBook(updateBookBO);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    this.bookApplicationService.deleteBook(id);
  }

  @GetMapping("")
  public PageResult<BookResponse> getBooks(@ModelAttribute BookPageRequest request) {
    PageList<BookBO> bookBOPageList = this.bookApplicationService.getBooks(request.getPageNum(),
        request.getPageSize());

    List<BookResponse> bookResponseList = this.bookConverter.toVO(bookBOPageList.getData());

    return PageResult.success(bookResponseList, bookBOPageList.getPageInfo());
  }

  @GetMapping("/list")
  public List<BookResponse> getAllBooks() {
    List<BookBO> bookBOList = this.bookApplicationService.getAllBooks();
    return this.bookConverter.toVO(bookBOList);
  }
}
