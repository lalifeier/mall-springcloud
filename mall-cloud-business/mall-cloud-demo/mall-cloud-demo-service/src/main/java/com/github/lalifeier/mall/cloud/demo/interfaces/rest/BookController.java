package com.github.lalifeier.mall.cloud.demo.interfaces.rest;

import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.api.book.BookApi;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.api.book.model.query.BookPageQuery;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookQueryApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController implements BookApi {
    private final BookApplicationService bookApplicationService;

    private final BookQueryApplicationService bookQueryApplicationService;

    @Override
    public void create(CreateBookCommand command) {
        bookApplicationService.create(command);
    }

    @Override
    public void update(Long id, UpdateBookCommand command) {
        command.setId(id);
        bookApplicationService.update(command);
    }

    @Override
    public void delete(Long id) {
        bookApplicationService.delete(id);
    }

    @Override
    public BookDTO get(Long id) {
        return bookQueryApplicationService.get(id);
    }

    @Override
    public PageResult<BookDTO> query(BookPageQuery query) {
        return bookQueryApplicationService.query(query);
    }
}
