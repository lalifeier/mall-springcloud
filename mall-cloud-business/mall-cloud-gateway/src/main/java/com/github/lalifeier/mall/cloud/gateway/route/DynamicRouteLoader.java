package com.github.lalifeier.mall.cloud.gateway.route;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.lalifeier.mall.cloud.gateway.config.GatewayRouteConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 动态路由加载器
 */
@Slf4j
@Component
//@RefreshScope
public class DynamicRouteLoader {
  private final GatewayRouteConfig gatewayRouteConfig;
  private final DynamicRouteService dynamicRouteService;
  private final RefreshRouteService refreshRouteService;

  private ConfigService configService;

  public DynamicRouteLoader(GatewayRouteConfig gatewayRouteConfig, DynamicRouteService dynamicRouteService, RefreshRouteService refreshRouteService) {
    this.gatewayRouteConfig = gatewayRouteConfig;
    this.dynamicRouteService = dynamicRouteService;
    this.refreshRouteService = refreshRouteService;

    try {
      this.configService = NacosFactory.createConfigService(gatewayRouteConfig.getServiceProperties());

      loadRouteDefinitions();

      registerNacosListener();
    } catch (NacosException e) {
      log.error("初始化网关路由时发生错误", e);
    }
  }

  private void registerNacosListener() {
    try {
      configService.addListener(gatewayRouteConfig.getDataId(), gatewayRouteConfig.getRouteGroup(), new NacosListener());
    } catch (NacosException e) {
      log.error("register nacos listener error:{}", e);
    }
  }

  private class NacosListener implements Listener {
    @Override
    public Executor getExecutor() {
      return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
      loadRouteDefinitions();
    }
  }

  private List<RouteDefinition> convert(String configInfo) {
    if (StringUtils.isBlank(configInfo)) {
      return Collections.emptyList();
    }
    return new Gson().fromJson(configInfo, new TypeToken<List<RouteDefinition>>() {
    }.getType());
  }

  private void loadRouteDefinitions() {
    try {
      String configInfo = configService.getConfig(gatewayRouteConfig.getDataId(), gatewayRouteConfig.getRouteGroup(), GatewayRouteConfig.TIMEOUT);
      List<RouteDefinition> routeDefinitions = convert(configInfo);
      for (RouteDefinition definition : routeDefinitions) {
        dynamicRouteService.save(definition);
      }
      refreshRouteService.refreshRoutes();
      log.info("Loaded {} route definitions from Nacos", routeDefinitions.size());
    } catch (Exception e) {
      log.error("loadRouteDefinitions error:{}", e);
    }
  }
}

