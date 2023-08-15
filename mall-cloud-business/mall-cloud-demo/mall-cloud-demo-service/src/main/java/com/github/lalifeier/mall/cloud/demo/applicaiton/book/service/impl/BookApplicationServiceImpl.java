package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.demo.api.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.assembler.BookAssembler;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookDomainService bookDomainService;

    private final BookRepository bookRepository;

    private final BookAssembler bookAssembler = BookAssembler.INSTANCE;

    @Override
    public void create(CreateBookCommand command) {
        Book book = this.bookAssembler.toEntity(command);
        bookDomainService.create(book);
    }

    @Override
    public void update(UpdateBookCommand command) {
        Book book = this.bookAssembler.toEntity(command);
        bookDomainService.update(book);
    }

    @Override
    public void delete(Long id) {
        BookId bookId = new BookId(id);
        bookDomainService.delete(bookId);
    }
}
