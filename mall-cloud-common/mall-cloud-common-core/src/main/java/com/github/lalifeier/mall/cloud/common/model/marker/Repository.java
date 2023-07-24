package com.github.lalifeier.mall.cloud.common.model.marker;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public interface Repository<T extends Aggregate<ID>, ID extends Identifier> {
  /**
   * 将一个Aggregate附属到一个Repository，让它变为可追踪。
   * Change-Tracking在下文会讲，非必须
   */
  void attach(@NotNull T aggregate);

  /**
   * 解除一个Aggregate的追踪
   * Change-Tracking在下文会讲，非必须
   */
  void detach(@NotNull T aggregate);

  /**
   * 通过ID寻找Aggregate。
   * 找到的Aggregate自动是可追踪的
   */
  T find(@NotNull ID id);


  /**
   * 通过IDs寻找Aggregate。
   * 找到的Aggregate自动是可追踪的
   */
  List<T> find(@NotEmpty Set<ID> ids);

  /**
   * 将一个Aggregate从Repository移除
   * 操作后的aggregate对象自动取消追踪
   */
  void remove(@NotNull T aggregate);

  /**
   * 保存一个Aggregate
   * 保存后自动重置追踪条件
   */
  void save(@NotNull T aggregate);
}
