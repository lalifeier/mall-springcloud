package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.common.event.DomainEventPublisher;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.assembler.BookAssembler;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.domain.book.event.BookCreatedEvent;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookRepository bookRepository;

    private final BookDomainService bookDomainService;

    private final DomainEventPublisher domainEventPublisher;
    private final BookAssembler bookAssembler = BookAssembler.INSTANCE;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateBookCommand command) {
        Book book = bookAssembler.toEntity(command);

        bookRepository.save(book);

        domainEventPublisher.publishEvent(new BookCreatedEvent(book));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateBookCommand command) {
        Book book = bookAssembler.toEntity(command);

        bookRepository.save(book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        BookId bookId = new BookId(id);

        Book book = bookRepository.find(bookId);
        if (book != null) {
            bookRepository.remove(book);
        }
    }
}
