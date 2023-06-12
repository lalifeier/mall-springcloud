package com.github.lalifeier.mall.cloud.gateway.route;

//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
///**
// * 动态更新路由网关service
// * 1）实现一个Spring提供的事件推送接口ApplicationEventPublisherAware
// * 2）提供动态路由的基础方法，可通过获取bean操作该类的方法。该类提供新增路由、更新路由、删除路由，然后实现发布的功能。
// */
//@Slf4j
//@Service
//public class DynamicRouteService {
//
//  //@Autowired
//  //private InMemoryRouteDefinitionRepository repository;
//
//  @Autowired
//  private RouteDefinitionWriter routeDefinitionWriter;
//
//  @Autowired
//  private RefreshRouteService refreshRouteService;
//
//  /**
//   * 删除路由
//   *
//   * @param id
//   * @return
//   */
//  public void delete(String id) {
//    routeDefinitionWriter.delete(Mono.just(id)).subscribe();
//    refreshRouteService.refreshRoutes();
//  }
//
//  /**
//   * 保存路由
//   *
//   * @param definition
//   * @return
//   */
//  public void save(RouteDefinition definition) {
//    routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//    refreshRouteService.refreshRoutes();
//  }
//}
//
//
