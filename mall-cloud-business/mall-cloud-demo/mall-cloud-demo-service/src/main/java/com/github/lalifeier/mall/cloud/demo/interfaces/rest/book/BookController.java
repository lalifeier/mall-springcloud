package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.common.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.UpdateBookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.BookPageRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  private final BookApplicationService bookApplicationService;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  @Autowired
  private StreamBridge streamBridge;

  public BookController(BookApplicationService bookApplicationService) {
    this.bookApplicationService = bookApplicationService;
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
    PageList<BookBO> bookBOPageList = this.bookApplicationService.getBooks(request);

    List<BookResponse> bookResponseList = this.bookConverter.toVO(bookBOPageList.getData());

    return PageResult.success(bookResponseList, bookBOPageList.getPageInfo());
  }

  //@GetMapping("/list")
  //public List<BookResponse> getAllBooks() {
  //  List<BookBO> bookBOList = this.bookApplicationService.getAllBooks();
  //  return this.bookConverter.toVO(bookBOList);
  //}

  @GetMapping("/send")
  public void send() {
    streamBridge.send("source1-out-0", "hello");
  }
}
