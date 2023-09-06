package com.github.lalifeier.mall.cloud.ratelimiter.algorithm;

import com.github.lalifeier.mall.cloud.ratelimiter.enums.RateLimitEnum;

public class TokenBucketRateLimiterAlgorithm extends AbstractRateLimiterAlgorithm {
    public TokenBucketRateLimiterAlgorithm() {
        super(RateLimitEnum.TOKEN_BUCKET.getScriptName());
    }

    @Override
    protected String getKeyName() {
        return RateLimitEnum.TOKEN_BUCKET.getKeyName();
    }
}
