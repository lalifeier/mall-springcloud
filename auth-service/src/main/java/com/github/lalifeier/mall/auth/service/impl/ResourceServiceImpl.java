package com.github.lalifeier.mall.auth.service.impl;

import com.github.lalifeier.mall.auth.constant.RedisConstant;
import com.github.lalifeier.mall.auth.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
@Service
public class ResourceServiceImpl implements ResourceService {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/account/hello", Arrays.asList("ADMIN"));
        resourceRolesMap.put("/account/user/currentUser", Arrays.asList("ADMIN", "USER"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
