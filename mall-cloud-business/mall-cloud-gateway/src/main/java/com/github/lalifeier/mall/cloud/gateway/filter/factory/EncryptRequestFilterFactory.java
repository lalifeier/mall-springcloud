package com.github.lalifeier.mall.cloud.gateway.filter.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.EncryptBodyUtil;
import com.github.lalifeier.mall.cloud.gateway.utils.WebFluxUtil;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class EncryptRequestFilterFactory extends AbstractGatewayFilterFactory<EncryptRequestFilterFactory.Config> {

    public EncryptRequestFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new EncryptRequestFilter(config, this);
    }

    @Data
    public static class Config {
        private String publicKey;
        private String privateKey;
        private List<String> whiteList;
        private List<String> blackList;
    }

    public class EncryptRequestFilter implements GatewayFilter, Ordered {

        private final Config config;
        private final GatewayFilterFactory<Config> gatewayFilterFactory;

        public EncryptRequestFilter(Config config, GatewayFilterFactory<Config> gatewayFilterFactory) {
            this.config = config;
            this.gatewayFilterFactory = gatewayFilterFactory;
        }

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private boolean shouldEncrypt(ServerWebExchange exchange, Config config) {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().toString();

            // 判断白名单和黑名单
            // if (WebFluxUtil.isPathMatch(path, config.getWhiteList())
            // || !WebFluxUtil.isPathMatch(path, config.getBlackList())) {
            // return false;
            // }

            HttpMethod method = request.getMethod();
            if (method != HttpMethod.POST) {
                return false;
            }

            // 判断是否为 JSON 请求
            if (!WebFluxUtil.isJsonRequest(exchange)) {
                return false;
            }

            return true;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            if (!shouldEncrypt(exchange, config)) {
                return chain.filter(exchange);
            }

            ServerRequest serverRequest = ServerRequest.create(
                    exchange, HandlerStrategies.withDefaults().messageReaders());

            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
                try {
                    JsonNode rootNode = objectMapper.readValue(body, JsonNode.class);
                    String encryptedData = rootNode.get("data").asText();
                    String key = rootNode.get("key").asText();
                    String decryptedBody = EncryptBodyUtil.decrypt(encryptedData, key, config.getPrivateKey());
                    return Mono.just(decryptedBody);
                } catch (JsonProcessingException e) {
                    log.error("Failed to parse JSON from encrypted request" + " body", e);
                    return Mono.error(e);
                } catch (Exception e) {
                    log.error("Failed to decrypt encrypted request body", e);
                    return Mono.error(e);
                }
            });

            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            headers.remove(HttpHeaders.CONTENT_LENGTH);

            BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter =
                    BodyInserters.fromPublisher(modifiedBody, String.class);
            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);

            return bodyInserter
                    .insert(outputMessage, new BodyInserterContext())
                    .then(Mono.defer(() -> {
                        ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return outputMessage.getBody();
                            }
                        };
                        return chain.filter(exchange.mutate().request(decorator).build());
                    }))
                    .onErrorResume(e -> {
                        String errorMessage = "Failed to decrypt request body";
                        byte[] bytes = errorMessage.getBytes(StandardCharsets.UTF_8);
                        DataBuffer buffer =
                                exchange.getResponse().bufferFactory().wrap(bytes);
                        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                        return exchange.getResponse().writeWith(Flux.just(buffer));
                    });

            // return chain.filter(exchange.mutate().request(new
            // EncryptRequestServerHttpRequest(exchange, this.config)).build());
        }

        @Override
        public int getOrder() {
            return Ordered.HIGHEST_PRECEDENCE;
        }
    }

    // @Slf4j
    // protected static class EncryptRequestServerHttpRequest extends ServerHttpRequestDecorator {
    // private final ServerWebExchange exchange;
    // private final Config config;
    //
    // private static final ObjectMapper objectMapper = new ObjectMapper();
    //
    // public EncryptRequestServerHttpRequest(ServerWebExchange exchange, Config config) {
    // super(exchange.getRequest());
    // this.exchange = exchange;
    // this.config = config;
    // }
    //
    // @Override
    // public Flux<DataBuffer> getBody() {
    // ServerRequest serverRequest = ServerRequest.create(exchange,
    // HandlerStrategies.withDefaults().messageReaders());
    //
    // Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
    // try {
    // EncryptBody encryptBody = objectMapper.readValue(body, EncryptBody.class);
    // String encryptedData = encryptBody.getData();
    // String key = encryptBody.getKey();
    // String decryptedBody = EncryptBodyUtil.decrypt(encryptedData, key,
    // config.getPrivateKey());
    // return Mono.just(decryptedBody);
    // } catch (JsonProcessingException e) {
    // log.error("Failed to parse JSON from encrypted request body", e);
    // return Mono.error(e);
    // } catch (Exception e) {
    // log.error("Failed to decrypt encrypted request body", e);
    // return Mono.error(e);
    // }
    // });
    //
    // BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter =
    // BodyInserters.fromPublisher(modifiedBody, String.class);
    // HttpHeaders headers = new HttpHeaders();
    // headers.putAll(exchange.getRequest().getHeaders());
    // headers.remove(HttpHeaders.CONTENT_LENGTH);
    // CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
    // return outputMessage.getBody();
    // }
    // }
}
