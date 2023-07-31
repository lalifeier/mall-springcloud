package com.github.lalifeier.mall.cloud.gateway.route;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

/** 动态更新路由网关service */
@Slf4j
@Service
public class DynamicRouteService {

    @Autowired private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired private RouteDefinitionLocator routeDefinitionLocator;

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public void delete(String id) {
        log.info("gateway delete route id {}", id);
        routeDefinitionWriter.delete(Mono.just(id)).subscribe();
    }

    /**
     * 保存路由
     *
     * @param definition
     * @return
     */
    public void save(RouteDefinition definition) {
        log.info("gateway save route {}", definition);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    }

    public void batchSave(List<RouteDefinition> definitions) {
        definitions.forEach(this::save);
    }

    public void fullUpdateRoute(List<RouteDefinition> definitions) {
        List<RouteDefinition> routeDefinitionsExits =
                routeDefinitionLocator.getRouteDefinitions().buffer().blockFirst();
        if (!CollectionUtils.isEmpty(routeDefinitionsExits)) {
            routeDefinitionsExits.forEach(
                    definition -> {
                        delete(definition.getId());
                    });
        }

        batchSave(definitions);
    }
}
