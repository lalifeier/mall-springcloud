package com.github.lalifeier.mall.cloud.gateway.route;

//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.github.lalifeier.mall.cloud.gateway.config.DynamicGatewayRouteConfig;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.Executor;
//
///**
// * 动态路由加载器
// */
//@Slf4j
//@Component
//public class DynamicRouteLoader {
//  private DynamicGatewayRouteConfig dynamicGatewayRouteConfig;
//  private DynamicRouteService dynamicRouteService;
//  private ConfigService configService;
//
//  public DynamicRouteLoader(DynamicGatewayRouteConfig dynamicGatewayRouteConfig, DynamicRouteService dynamicRouteService) throws NacosException {
//    if (!dynamicGatewayRouteConfig.getEnabled()) {
//      return;
//    }
//
//    this.dynamicGatewayRouteConfig = dynamicGatewayRouteConfig;
//    this.dynamicRouteService = dynamicRouteService;
//    this.configService = NacosFactory.createConfigService(dynamicGatewayRouteConfig.getServiceProperties());
//
//    registerNacosListener();
//    loadRouteDefinitions();
//  }
//
//  private void registerNacosListener() {
//    try {
//      this.configService.addListener(dynamicGatewayRouteConfig.getDataId(), dynamicGatewayRouteConfig.getRouteGroup(), new NacosListener());
//    } catch (NacosException e) {
//      log.error("register nacos listener error:{}", e);
//    }
//  }
//
//  private class NacosListener implements Listener {
//    @Override
//    public Executor getExecutor() {
//      return null;
//    }
//
//    @Override
//    public void receiveConfigInfo(String configInfo) {
//      loadRouteDefinitions();
//
//      //CompletableFuture.supplyAsync(() -> {
//      //  try {
//      //    return convert(configInfo);
//      //  } catch (IOException e) {
//      //    log.error("loadRouteDefinitions error:{}", e);
//      //    return Collections.emptyList();
//      //  }
//      //}).thenAcceptAsync(routeDefinitions -> {
//      //  routeDefinitions.parallelStream().forEach(dynamicRouteService::save);
//      //  log.info("Loaded {} route definitions from Nacos", routeDefinitions.size());
//      //});
//    }
//  }
//
//  private List<RouteDefinition> convert(String configInfo) throws IOException {
//    if (StringUtils.isBlank(configInfo)) {
//      return Collections.emptyList();
//    }
//    List<RouteDefinition> routeDefinitions = new Gson().fromJson(configInfo, new TypeToken<List<RouteDefinition>>() {
//    }.getType());
//    log.info("Converted Nacos config to {} route definitions", routeDefinitions.size());
//    return routeDefinitions;
//  }
//
//  private void loadRouteDefinitions() {
//    try {
//      String configInfo = configService.getConfig(dynamicGatewayRouteConfig.getDataId(), dynamicGatewayRouteConfig.getRouteGroup(), DynamicGatewayRouteConfig.TIMEOUT);
//      List<RouteDefinition> routeDefinitions = convert(configInfo);
//      routeDefinitions.parallelStream().forEach(dynamicRouteService::save);
//      log.info("Loaded {} route definitions from Nacos", routeDefinitions.size());
//    } catch (Exception e) {
//      log.error("loadRouteDefinitions error:{}", e);
//    }
//  }
//}

