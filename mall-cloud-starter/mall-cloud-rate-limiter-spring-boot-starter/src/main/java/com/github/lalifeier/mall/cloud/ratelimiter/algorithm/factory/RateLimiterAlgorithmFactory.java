package com.github.lalifeier.mall.cloud.ratelimiter.algorithm.factory;

import com.github.lalifeier.mall.cloud.ratelimiter.algorithm.RateLimiterAlgorithm;
import com.github.lalifeier.mall.cloud.ratelimiter.algorithm.TokenBucketRateLimiterAlgorithm;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class RateLimiterAlgorithmFactory {
    private static final Map<String, RateLimiterAlgorithm> ALGORITHM_MAP =
            new ConcurrentHashMap<>(16);

    static {
        ALGORITHM_MAP.put("tokenBucket", new TokenBucketRateLimiterAlgorithm());
    }

    private RateLimiterAlgorithmFactory() {}

    public static RateLimiterAlgorithm getRateLimiterAlgorithm(String name) {
        return ALGORITHM_MAP.getOrDefault(name, new TokenBucketRateLimiterAlgorithm());
    }
}
