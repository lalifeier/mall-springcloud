package com.github.lalifeier.mall.cloud.demo.domain.book.service.impl;

import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookDomainServiceImpl implements BookDomainService {

    private final BookRepository bookRepository;
}
