package com.github.lalifeier.mall.cloud.gateway.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    ServerHttpResponse response = exchange.getResponse();
    if (response.isCommitted()) {
      return Mono.error(ex);
    }

    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    if (ex instanceof ResponseStatusException) {
      response.setStatusCode(((ResponseStatusException) ex).getStatusCode());
    }

    return response.writeWith(Mono.fromSupplier(() -> {
      DataBufferFactory bufferFactory = response.bufferFactory();
      try {
        return bufferFactory
            .wrap(objectMapper.writeValueAsBytes(Result.failure(500, ex.getMessage())));
      } catch (JsonProcessingException e) {
        log.error("Error writing response", ex);
        return bufferFactory.wrap(new byte[0]);
      }
    }));
  }
}
