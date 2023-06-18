package com.github.lalifeier.mall.cloud.ratelimiter.algorithm;

import com.github.lalifeier.mall.cloud.ratelimiter.constant.Constants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.List;

public abstract class AbstractRateLimiterAlgorithm implements RateLimiterAlgorithm {
  private final String scriptName;

  private final RedisScript<Long> script;

  protected AbstractRateLimiterAlgorithm(String scriptName) {
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    String scriptPath = Constants.SCRIPT_PATH + scriptName;
    redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptPath)));
    redisScript.setResultType(Long.class);

    this.script = redisScript;
    this.scriptName = scriptName;
  }

  @Override
  public String getScriptName() {
    return scriptName;
  }

  @Override
  public RedisScript<Long> getScript() {
    return script;
  }

  @Override
  public List<String> getKeys(String key) {
    List<String> keys = Collections.singletonList(key);
    return keys;
  }
}
