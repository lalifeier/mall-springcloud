package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.common.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.command.BookCommandApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.query.BookQueryApplicationService;
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
  private final BookCommandApplicationService bookCommandApplicationService;

  private final BookQueryApplicationService bookQueryApplicationService;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookController(BookCommandApplicationService bookCommandApplicationService, BookQueryApplicationService bookQueryApplicationService) {
    this.bookCommandApplicationService = bookCommandApplicationService;
    this.bookQueryApplicationService = bookQueryApplicationService;
  }


  @PostMapping("")
  public void createBook(@Validated @RequestBody CreateBookRequest createBookRequest) {
    CreateBookCommand createBookBO = bookConverter.toDTO(createBookRequest);
    this.bookCommandApplicationService.createBook(createBookBO);
  }

  @PutMapping("/{id}")
  public void updateBook(@PathVariable Long id, @Validated @RequestBody UpdateBookRequest updateBookRequest) {
    UpdateBookCommand updateBookCommand = bookConverter.toDTO(updateBookRequest);
    updateBookCommand.setId(id);
    this.bookCommandApplicationService.updateBook(updateBookCommand);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    this.bookCommandApplicationService.deleteBook(id);
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable Long id) {
    BookDTO bookDTO = this.bookQueryApplicationService.getBookById(id);
    return this.bookConverter.toVO(bookDTO);
  }

  @GetMapping("")
  public PageResult<BookResponse> getBooks(@ModelAttribute BookPageQuery request) {
    PageList<BookDTO> bookBOPageList = this.bookQueryApplicationService.getBooks(request);

    List<BookResponse> bookResponseList = this.bookConverter.toVO(bookBOPageList.getData());

    return PageResult.success(bookResponseList, bookBOPageList.getPageInfo());
  }

  //@GetMapping("/list")
  //public List<BookResponse> getAllBooks() {
  //  List<BookBO> bookBOList = this.bookApplicationService.getAllBooks();
  //  return this.bookConverter.toVO(bookBOList);
  //}

}
