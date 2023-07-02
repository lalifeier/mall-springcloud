package com.github.lalifeier.mall.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class GatewayRouteConfig {
  private Boolean enabled;
  private String serverAddr;
  private String namespace;
  private String dataId;
  private String routeGroup;
  private String username;
  private String password;
  public static final long TIMEOUT = 30000;

  public GatewayRouteConfig(
    @Value("${route.dynamic.enabled}") Boolean enabled,
    @Value("${route.dynamic.dataId}") String dataId,
    @Value("${spring.cloud.nacos.config.namespace}") String namespace,
    @Value("${spring.cloud.nacos.config.group}") String routeGroup,
    @Value("${spring.cloud.nacos.config.server-addr}") String serverAddr,
    @Value("${spring.cloud.nacos.config.username}") String username,
    @Value("${spring.cloud.nacos.config.password}") String password
  ) {
    this.enabled = enabled;
    this.dataId = dataId;
    this.namespace = namespace;
    this.routeGroup = routeGroup;
    this.serverAddr = serverAddr;
    this.username = username;
    this.password = password;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getServerAddr() {
    return serverAddr;
  }

  public void setServerAddr(String serverAddr) {
    this.serverAddr = serverAddr;
  }

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getDataId() {
    return dataId;
  }

  public void setDataId(String dataId) {
    this.dataId = dataId;
  }

  public String getRouteGroup() {
    return routeGroup;
  }

  public void setRouteGroup(String routeGroup) {
    this.routeGroup = routeGroup;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Properties getServiceProperties() {
    Properties properties = new Properties();
    properties.setProperty("serverAddr", serverAddr);
    properties.putIfAbsent("namespace", namespace);
    properties.putIfAbsent("username", username);
    properties.putIfAbsent("password", password);
    return properties;
  }
}

