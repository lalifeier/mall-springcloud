package com.github.lalifeier.mall.cloud.demo.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository.BookQueryRepository;
import com.github.lalifeier.mall.cloud.demo.book.dto.query.BookPageQuery;
import com.github.lalifeier.mall.cloud.demo.book.dto.query.BookQuery;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.po.BookPO;
import com.github.lalifeier.mall.cloud.mybatisplus.converter.PageConverter;
import com.github.lalifeier.mall.cloud.mybatisplus.utils.QueryWrapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookQueryRepositoryImpl implements BookQueryRepository {
    private final BookMapper bookMapper;

    private final BookConverter bookConverter = BookConverter.INSTANCE;

    @Override
    public Pagination<Book> query(BookPageQuery query) {
        Page<BookPO> page = new Page<>(query.getPageNum(), query.getPageSize());

        QueryWrapper<BookPO> queryWrapper =
                QueryWrapperUtils.buildQueryWrapper(BookPO.class, query);

        IPage<BookPO> bookPage = bookMapper.selectPage(page, queryWrapper);

        return PageConverter.convert(bookPage, bookConverter::fromData);
    }

    @Override
    public Long count(BookQuery query) {
        QueryWrapper<BookPO> queryWrapper =
                QueryWrapperUtils.buildQueryWrapper(BookPO.class, query);

        return bookMapper.selectCount(queryWrapper);
    }
}
