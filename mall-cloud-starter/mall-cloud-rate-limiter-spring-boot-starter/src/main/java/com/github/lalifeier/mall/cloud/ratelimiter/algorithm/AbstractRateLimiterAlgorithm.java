package com.github.lalifeier.mall.cloud.ratelimiter.algorithm;

import com.github.lalifeier.mall.cloud.ratelimiter.constant.Constants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractRateLimiterAlgorithm implements RateLimiterAlgorithm<List<Long>> {
  private final String scriptName;

  private final RedisScript<List<Long>> script;

  protected abstract String getKeyName();

  protected AbstractRateLimiterAlgorithm(String scriptName) {
    DefaultRedisScript redisScript = new DefaultRedisScript<>();
    String scriptPath = Constants.SCRIPT_PATH + scriptName;
    redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptPath)));
    redisScript.setResultType(List.class);

    this.script = redisScript;
    this.scriptName = scriptName;
  }

  @Override
  public String getScriptName() {
    return scriptName;
  }

  @Override
  public RedisScript<List<Long>> getScript() {
    return script;
  }
  
  @Override
  public List<String> getKeys(String key) {
    String prefix = getKeyName() + ".{" + key;
    String tokenKey = prefix + "}.tokens";
    String timestampKey = prefix + "}.timestamp";
    return Arrays.asList(tokenKey, timestampKey);
  }
}
