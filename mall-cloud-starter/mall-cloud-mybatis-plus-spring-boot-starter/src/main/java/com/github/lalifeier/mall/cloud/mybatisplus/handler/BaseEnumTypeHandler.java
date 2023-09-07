package com.github.lalifeier.mall.cloud.mybatisplus.handler;

// public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum<T>, T extends Serializable>
// extends BaseTypeHandler<BaseEnum<T>> {
//
// private final Class<E> type;
//
// public BaseEnumTypeHandler(Class<E> type) {
// if (type == null) {
// throw new IllegalArgumentException("Type argument cannot be null");
// }
// this.type = type;
// }
//
// @Override
// public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum<T> parameter, JdbcType
// jdbcType)
// throws SQLException {
// ps.setObject(i, parameter.getCode());
// }
//
// @Override
// public BaseEnum<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
// T code = rs.getObject(columnName, getType());
// return toEnum(code);
// }
//
// @Override
// public BaseEnum<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
// T code = rs.getObject(columnIndex, getType());
// return toEnum(code);
// }
//
// @Override
// public BaseEnum<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
// T code = cs.getObject(columnIndex, getType());
// return toEnum(code);
// }
//
// private E toEnum(T code) {
// if (code == null) {
// return null;
// }
// for (E e : type.getEnumConstants()) {
// if (e.getCode().equals(code)) {
// return e;
// }
// }
// throw new IllegalArgumentException("Unknown enum type " + code + ", please check your enum
// mapping");
// }
//
// private Class<T> getType() {
// // 获取 BaseEnum 接口的泛型类型
// ParameterizedType parameterizedType = (ParameterizedType) type.getGenericInterfaces()[0];
// return (Class<T>) parameterizedType.getActualTypeArguments()[0];
// }
// }
