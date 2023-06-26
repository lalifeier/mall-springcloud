package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookQueryApplicationService;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.BookPageQuery;
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
  public void createBook(@Validated @RequestBody CreateBookRequest createBookRequest) {
    CreateBookCommand createBookBO = bookConverter.toDTO(createBookRequest);
    this.bookApplicationService.create(createBookBO);
  }

  @PutMapping("/{id}")
  public void updateBook(@PathVariable Long id, @Validated @RequestBody UpdateBookRequest updateBookRequest) {
    UpdateBookCommand updateBookCommand = bookConverter.toDTO(updateBookRequest);
    updateBookCommand.setId(id);
    this.bookApplicationService.update(updateBookCommand);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    this.bookApplicationService.delete(id);
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable Long id) {
    BookDTO bookDTO = this.bookQueryApplicationService.getBookById(id);
    return this.bookConverter.toVO(bookDTO);
  }

  @GetMapping("")
  public PageResult<BookResponse> getBooks(@ModelAttribute BookPageQuery request) {
    Pagination<BookDTO> bookBOPagination = this.bookQueryApplicationService.getBooks(request);

    List<BookResponse> bookResponseList = this.bookConverter.toVO(bookBOPagination.getData());

    return PageResult.success(bookResponseList, bookBOPagination.getPageInfo());
  }

  //@GetMapping("/list")
  //public List<BookResponse> getAllBooks() {
  //  List<BookBO> bookBOList = this.bookApplicationService.getAllBooks();
  //  return this.bookConverter.toVO(bookBOList);
  //}

}
