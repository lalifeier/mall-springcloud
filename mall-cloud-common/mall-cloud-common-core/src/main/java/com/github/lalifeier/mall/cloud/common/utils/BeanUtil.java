package com.github.lalifeier.mall.cloud.common.utils;

// import lombok.extern.slf4j.Slf4j;
// import org.apache.commons.beanutils.BeanUtilsBean;
// import org.apache.commons.beanutils.ConvertUtilsBean;
// import org.apache.commons.beanutils.Converter;
//
// import java.util.List;
// import java.util.stream.Collectors;
//
/// **
// * 用于将一个对象的属性复制到另一个对象中。
// */
// @Slf4j
// public class BeanUtil {
//
// private static ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
//
// static {
//// convertUtils.register(new DateConverter(), Date.class);
// }
//
// private BeanUtil() {
// }
//
// /**
// * 将源对象的属性复制到目标对象中。
// *
// * @param source 源对象
// * @param target 目标对象
// */
// public static void copyProperties(Object source, Object target) {
// copyProperties(source, target, null);
// }
//
// /**
// * 将源对象的属性复制到目标对象中，并使用自定义转换器进行属性转换。
// *
// * @param source 源对象
// * @param target 目标对象
// * @param converter 自定义转换器
// */
// public static void copyProperties(Object source, Object target, Converter converter) {
// if (converter != null) {
// convertUtils.register(converter, Object.class);
// }
// try {
// BeanUtilsBean.getInstance().copyProperties(target, source);
// } catch (Exception e) {
// log.error(e.getMessage());
// }
// }
//
// /**
// * 将源对象的属性复制到新创建的目标对象中。
// *
// * @param source 源对象
// * @param targetClass 目标对象的类
// * @return 复制后的目标对象
// */
// public static <T> T copyBean(Object source, Class<T> targetClass) {
// return copyBean(source, targetClass, null);
// }
//
// /**
// * 将源对象的属性复制到新创建的目标对象中，并使用自定义转换器进行属性转换。
// *
// * @param source 源对象
// * @param targetClass 目标对象的类
// * @param converter 自定义转换器
// * @return 复制后的目标对象
// */
// public static <T> T copyBean(Object source, Class<T> targetClass, Converter converter) {
// T target;
// try {
// target = targetClass.getDeclaredConstructor().newInstance();
// } catch (Exception e) {
// throw new RuntimeException("Failed to create an instance of the target class: " +
// targetClass.getName(), e);
// }
// copyProperties(source, target, converter);
// return target;
// }
//
// /**
// * 将源对象列表中的每个对象复制到新创建的目标对象列表中。
// *
// * @param sourceList 源对象列表
// * @param targetClass 目标对象的类
// * @return 复制后的目标对象列表
// */
// public static <T> List<T> copyBeanList(List<?> sourceList, Class<T> targetClass) {
// return copyBeanList(sourceList, targetClass, null);
// }
//
// /**
// * 将源对象列表中的每个对象复制到新创建的目标对象列表中。
// *
// * @param sourceList 源对象列表
// * @param targetClass 目标对象的类
// * @param converter 自定义转换器
// * @return 复制后的目标对象列表
// */
// public static <T> List<T> copyBeanList(List<?> sourceList, Class<T> targetClass, Converter
// converter) {
// return sourceList.stream()
// .map(source -> copyBean(source, targetClass, converter))
// .collect(Collectors.toList());
// }
// }
