package com.github.lalifeier.mall.cloud.demo.api.book;

import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.api.book.model.query.BookPageQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "图书管理")
public interface BookApi {

    @Operation(summary = "添加图书")
    @PostMapping("")
    void create(@Validated @RequestBody CreateBookCommand command);

    @Operation(summary = "更新指定id图书")
    @Parameter(name = "id", description = "图书id", in = ParameterIn.PATH)
    @PutMapping("/{id}")
    void update(
            @PathVariable @Positive(message = "bookId必须为正整数") Long id,
            @Validated @RequestBody UpdateBookCommand command);

    @Operation(summary = "删除指定id图书")
    @Parameter(name = "id", description = "图书id", in = ParameterIn.PATH)
    @DeleteMapping("/{id}")
    void delete(@PathVariable @Positive(message = "bookId必须为正整数") Long id);

    @Operation(summary = "获取指定id图书")
    @Parameter(name = "id", description = "图书id", in = ParameterIn.PATH)
    @GetMapping("/{id}")
    BookDTO get(@PathVariable @Positive(message = "bookId必须为正整数") Long id);

    @Operation(summary = "查询图书列表")
    @GetMapping("")
    PageResult<BookDTO> query(@Valid @ModelAttribute BookPageQuery query);
}
