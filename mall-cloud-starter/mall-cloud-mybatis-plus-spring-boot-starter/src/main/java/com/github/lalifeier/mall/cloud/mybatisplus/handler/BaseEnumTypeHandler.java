package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum<E, T>, T> extends BaseTypeHandler<E> {
  private final Class<E> type;

  public BaseEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
      throws SQLException {
    if (jdbcType == null) {
      ps.setObject(i, parameter.getCode());
    } else {
      ps.setObject(i, parameter.getCode(), jdbcType.TYPE_CODE);
    }
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    getCodeType();
    T code = (T) rs.getObject(columnName);
    return rs.wasNull() ? null : BaseEnum.parse(type, code);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    T code = (T) rs.getObject(columnIndex);
    return rs.wasNull() ? null : BaseEnum.parse(type, rs.getObject(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    T code = (T) cs.getObject(columnIndex);
    return cs.wasNull() ? null : BaseEnum.parse(type, code);
  }

  private Class<T> getCodeType() {
    ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
    Type[] actualTypeArguments = type.getActualTypeArguments();
    if (actualTypeArguments.length == 2) {
      return (Class<T>) actualTypeArguments[1];
    } else {
      throw new IllegalStateException("Invalid type arguments");
    }
  }
}
