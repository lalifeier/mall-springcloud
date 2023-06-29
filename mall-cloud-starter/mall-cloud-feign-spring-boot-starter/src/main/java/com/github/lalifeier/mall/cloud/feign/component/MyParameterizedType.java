package com.github.lalifeier.mall.cloud.feign.component;

//public class MyParameterizedType implements ParameterizedType {
//
//  private Type type;
//
//  /**
//   * 将Feign Client方法的返回值注入，只要两种类型，一种是ParameterizedTypeImpl，另一种是具体的Class对象
//   */
//  public MyParameterizedType(Type type) {
//    this.type = type;
//  }
//
//  /**
//   * 属性Type就是BaseResponse的泛型类型，直接返回type就可以
//   */
//  @Override
//  public Type[] getActualTypeArguments() {
//    Type[] types = new Type[1];
//    types[0] = type;
//    return types;
//  }
//
//  /**
//   * 最外层的类型就是我们要与type合并的BaseResponse类型
//   */
//  @Override
//  public Type getRawType() {
//    return Result.class;
//  }
//
//  @Override
//  public Type getOwnerType() {
//    if (type instanceof ParameterizedTypeImpl) {
//      ParameterizedTypeImpl typeImpl = (ParameterizedTypeImpl) type;
//      return typeImpl.getRawType().getEnclosingClass();
//    }
//
//    if (type instanceof Class) {
//      return ((Class) type).getEnclosingClass();
//    }
//
//    return null;
//  }
//}
