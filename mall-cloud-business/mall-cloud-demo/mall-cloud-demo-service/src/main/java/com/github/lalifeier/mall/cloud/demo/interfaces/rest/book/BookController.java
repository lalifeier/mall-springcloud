package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookQueryApplicationService;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.BookAbstractPageQuery;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.response.BookResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
  private final BookApplicationService bookApplicationService;

  private final BookQueryApplicationService bookQueryApplicationService;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookController(BookApplicationService bookApplicationService, BookQueryApplicationService bookQueryApplicationService) {
    this.bookApplicationService = bookApplicationService;
    this.bookQueryApplicationService = bookQueryApplicationService;
  }

  @PostMapping("")
  public void create(@Validated @RequestBody CreateBookRequest createBookRequest) {
    CreateBookCommand createBookBO = bookConverter.toDTO(createBookRequest);
    this.bookApplicationService.create(createBookBO);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @Validated @RequestBody UpdateBookRequest updateBookRequest) {
    UpdateBookCommand updateBookCommand = bookConverter.toDTO(updateBookRequest);
    updateBookCommand.setId(id);
    this.bookApplicationService.update(updateBookCommand);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    this.bookApplicationService.delete(id);
  }

  @GetMapping("/{id}")
  public BookResponse get(@PathVariable Long id) {
    BookDTO bookDTO = this.bookQueryApplicationService.get(id);
    return this.bookConverter.toVO(bookDTO);
  }

  @GetMapping("")
  public PageResult<BookResponse> query(@ModelAttribute BookAbstractPageQuery query) {
    Pagination<BookDTO> bookDTOPagination = this.bookQueryApplicationService.query(query);

    List<BookResponse> bookResponseList = this.bookConverter.toVO(bookDTOPagination.getData());

    return PageResult.success(bookResponseList, bookDTOPagination.getPageInfo());
  }
}
