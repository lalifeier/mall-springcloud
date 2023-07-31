package com.github.lalifeier.mall.cloud.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.interfaces.Join;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupperMapper<T> extends BaseMapper<T> {

    /** 共享锁 */
    String LAST_SQL_LOCK_IN_SHARE_MODE = "LOCK IN SHARE MODE";

    /** 排它锁 */
    String LAST_SQL_FOR_UPDATE = "FOR UPDATE";

    /** 默认批量大小 */
    int DEFAULT_BATCH_SIZE = 1000;

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

    /**
     * 查询一条记录并加排它锁
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 记录
     */
    default T selectOneForUpdate(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        ((Join<?>) queryWrapper).last(LAST_SQL_FOR_UPDATE);
        return selectOne(queryWrapper);
    }

    /**
     * 查询记录列表并加排它锁
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 记录列表
     */
    default List<T> selectListForUpdate(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        ((Join<?>) queryWrapper).last(LAST_SQL_FOR_UPDATE);
        return selectList(queryWrapper);
    }

    /**
     * 查询一条记录并加共享锁
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 记录
     */
    default T selectOneLockInShareMode(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        ((Join<?>) queryWrapper).last(LAST_SQL_LOCK_IN_SHARE_MODE);
        return selectOne(queryWrapper);
    }

    /**
     * 查询记录列表并加共享锁
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 记录列表
     */
    default List<T> selectListLockInShareMode(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        ((Join<?>) queryWrapper).last(LAST_SQL_LOCK_IN_SHARE_MODE);
        return selectList(queryWrapper);
    }
}
