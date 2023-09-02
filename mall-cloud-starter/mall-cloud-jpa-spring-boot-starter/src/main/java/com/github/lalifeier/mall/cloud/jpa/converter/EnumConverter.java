package com.github.lalifeier.mall.cloud.jpa.converter;

// public abstract class EnumConverter<E extends Enum<E>, T> implements AttributeConverter<E, T> {
//
// private final Class<E> clazz;
//
// @SuppressWarnings("unchecked")
// public EnumConverter() {
// this.clazz = (Class<E>) (((ParameterizedType)
// this.getClass().getGenericSuperclass()).getActualTypeArguments())[0];
// }
//
// @Override
// public T convertToDatabaseColumn(E attribute) {
// return Optional.ofNullable(attribute)
// .map(IEnum::getValue)
// .orElse(null);
// }
//
// @Override
// public E convertToEntityAttribute(T dbData) {
// return Arrays.stream(clazz.getEnumConstants())
// .filter(iEnum -> iEnum.getValue().equals(dbData))
// .findFirst()
// .orElse(null);
// }
// }
//
