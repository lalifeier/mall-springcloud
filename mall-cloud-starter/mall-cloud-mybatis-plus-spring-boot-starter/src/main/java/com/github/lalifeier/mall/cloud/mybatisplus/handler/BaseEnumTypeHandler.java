package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum<E, T>, T> extends BaseTypeHandler<E> {
    private final Class<E> type;

    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        T code = (T) rs.getObject(columnName);
        return BaseEnum.parse(type, code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        T code = (T) rs.getObject(columnIndex);
        return BaseEnum.parse(type, code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        T code = (T) cs.getObject(columnIndex);
        return BaseEnum.parse(type, code);
    }
}
