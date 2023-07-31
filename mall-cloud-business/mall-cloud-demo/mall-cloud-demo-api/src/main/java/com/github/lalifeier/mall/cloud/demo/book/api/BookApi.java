package com.github.lalifeier.mall.cloud.demo.book.api;

import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.book.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.book.dto.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.book.dto.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.book.dto.query.BookPageQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
public interface BookApi {
    @PostMapping("")
    void create(@Validated @RequestBody CreateBookCommand command);

    @PutMapping("/{id}")
    void update(
            @PathVariable @Positive(message = "bookId必须为正整数") Long id,
            @Validated @RequestBody UpdateBookCommand command);

    @DeleteMapping("/{id}")
    void delete(@PathVariable @Positive(message = "bookId必须为正整数") Long id);

    @GetMapping("/{id}")
    BookDTO get(@PathVariable @Positive(message = "bookId必须为正整数") Long id);

    @GetMapping("")
    PageResult<BookDTO> query(@Valid @ModelAttribute BookPageQuery query);
}
