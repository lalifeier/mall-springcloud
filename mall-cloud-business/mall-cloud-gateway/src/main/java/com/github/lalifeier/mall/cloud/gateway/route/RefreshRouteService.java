package com.github.lalifeier.mall.cloud.gateway.route;

//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class RefreshRouteService implements ApplicationEventPublisherAware {
//
//  private ApplicationEventPublisher publisher;
//
//  @Override
//  public void setApplicationEventPublisher(@NotNull ApplicationEventPublisher publisher) {
//    this.publisher = publisher;
//  }
//
//  /**
//   * 刷新路由表
//   */
//  public void refreshRoutes() {
//    publisher.publishEvent(new RefreshRoutesEvent(this));
//  }
//}
