package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po.BookPO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BookMapper extends BaseMapper<BookPO> {
}
