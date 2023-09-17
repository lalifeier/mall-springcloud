package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.lalifeier.mall.cloud.common.annocation.EnumValue;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

public class MybatisEnumTypeHandler<E extends Enum<E> & BaseEnum<E, T>, T>
    extends BaseTypeHandler<E> {

  private static final Map<String, String> TABLE_METHOD_OF_ENUM_TYPES = new ConcurrentHashMap<>();
  private static final ReflectorFactory REFLECTOR_FACTORY = new DefaultReflectorFactory();

  private final Class<E> enumClass;

  private final Class<?> propertyType;

  private final Invoker getInvoker;

  public MybatisEnumTypeHandler(Class<E> enumClass) {
    if (enumClass == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.enumClass = enumClass;

    MetaClass metaClass = MetaClass.forClass(enumClass, REFLECTOR_FACTORY);

    String name = "code";
    if (!BaseEnum.class.isAssignableFrom(enumClass)) {
      name = findEnumValueFieldName(this.enumClass).orElseThrow(() -> new IllegalArgumentException(
          String.format("Could not find @EnumValue in Class: %s.", enumClass.getName())));
    }

    this.propertyType = ReflectionKit.resolvePrimitiveIfNecessary(metaClass.getGetterType(name));
    this.getInvoker = metaClass.getGetInvoker(name);
  }

  public static Optional<String> findEnumValueFieldName(Class<?> clazz) {
    if (clazz != null && clazz.isEnum()) {
      String className = clazz.getName();
      return Optional.ofNullable(
          CollectionUtils.computeIfAbsent(TABLE_METHOD_OF_ENUM_TYPES, className, key -> {
            Optional<Field> fieldOptional = findEnumValueAnnotationField(clazz);
            return fieldOptional.map(Field::getName).orElse(null);
          }));
    }
    return Optional.empty();
  }

  private static Optional<Field> findEnumValueAnnotationField(Class<?> clazz) {
    return Arrays.stream(clazz.getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(EnumValue.class)).findFirst();
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
      throws SQLException {
    if (jdbcType == null) {
      ps.setObject(i, getValue(parameter));
    } else {
      ps.setObject(i, getValue(parameter), jdbcType.TYPE_CODE);
    }
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    Object value = rs.getObject(columnName, this.propertyType);
    if (null == value && rs.wasNull()) {
      return null;
    }
    return this.valueOf(value);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Object value = rs.getObject(columnIndex, this.propertyType);
    if (null == value && rs.wasNull()) {
      return null;
    }
    return this.valueOf(value);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    Object value = cs.getObject(columnIndex, this.propertyType);
    if (null == value && cs.wasNull()) {
      return null;
    }
    return this.valueOf(value);
  }

  private Object getValue(Object object) {
    try {
      return this.getInvoker.invoke(object, new Object[0]);
    } catch (ReflectiveOperationException e) {
      throw ExceptionUtils.mpe(e);
    }
  }

  private boolean equalsValue(Object sourceValue, Object targetValue) {
    String sValue = StringUtils.toStringTrim(sourceValue);
    String tValue = StringUtils.toStringTrim(targetValue);
    if (sourceValue instanceof Number && targetValue instanceof Number
        && new BigDecimal(sValue).compareTo(new BigDecimal(tValue)) == 0) {
      return true;
    }
    return Objects.equals(sValue, tValue);
  }

  private E valueOf(Object value) {
    E[] es = enumClass.getEnumConstants();
    return Arrays.stream(es).filter((e) -> equalsValue(value, getValue(e))).findAny().orElse(null);
  }
}
