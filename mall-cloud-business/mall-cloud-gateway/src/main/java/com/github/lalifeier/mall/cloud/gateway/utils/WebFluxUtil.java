package com.github.lalifeier.mall.cloud.gateway.utils;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

public final class WebFluxUtil {

  private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

  /**
   * 判断路径是否与路径模式匹配
   *
   * @param patterns 路径模式字符串List
   * @param path     url
   * @return 是否匹配
   */
  public static boolean isPathMatch(String path, List<String> patterns) {
    for (String pattern : patterns) {
      if (PATH_MATCHER.match(pattern, path)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 获取原请求路径
   */
  public static String getOriginalRequestUrl(ServerWebExchange exchange) {
    ServerHttpRequest request = exchange.getRequest();
    LinkedHashSet<URI> uris = exchange.getRequiredAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
    URI requestUri = uris.stream().findFirst().orElse(request.getURI());
    return UriComponentsBuilder.fromPath(requestUri.getRawPath()).build().toUriString();
  }

  /**
   * 是否是Json请求
   *
   * @param exchange HTTP请求
   */
  public static boolean isJsonRequest(ServerWebExchange exchange) {
    String header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
    return StringUtils.startsWithIgnoreCase(header, MediaType.APPLICATION_JSON_VALUE);
  }

  //public static Mono<Void> writeJsonResponse(ServerHttpResponse response, Result<String> result) {
  //  response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
  //  response.setStatusCode(response.getStatusCode());
  //
  //  String jsonResult = JSON.toJSONString(result);
  //  byte[] bytes = jsonResult.getBytes(StandardCharsets.UTF_8);
  //
  //  DataBuffer buffer = response.bufferFactory().wrap(bytes);
  //  return response.writeWith(Flux.just(buffer));
  //}


  /**
   * 读取request内的body
   *
   * @param serverHttpRequest
   * @return
   */
  public static String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
    Flux<DataBuffer> body = serverHttpRequest.getBody();
    AtomicReference<String> bodyRef = new AtomicReference<>();
    body.subscribe(buffer -> {
      CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
      DataBufferUtils.release(buffer);
      bodyRef.set(charBuffer.toString());
    });
    return bodyRef.get();
  }

  /**
   * 从缓存中读取request内的body
   *
   * @return body
   */
  public static String resolveBodyFromCacheRequest(ServerWebExchange exchange) {
    Object obj = exchange.getAttributes().get(ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR);
    if (ObjectUtil.isNull(obj)) {
      return null;
    }
    DataBuffer buffer = (DataBuffer) obj;
    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
    return charBuffer.toString();
  }


}
