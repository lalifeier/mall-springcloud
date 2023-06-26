package com.github.lalifeier.mall.cloud.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupperMapper<T> extends BaseMapper<T> {
  /**
   * 批量插入 拼接sql模式
   *
   * @param entityList list
   * @return 返回影响行数
   */
  int insertBatchSomeColumn(List<T> entityList);

  /**
   * 批量更新表中的字段 根据id
   *
   * @param entity 要更新的字段和id
   * @return 影响行数
   */
  int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
}
