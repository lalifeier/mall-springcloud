package com.github.lalifeier.mall.cloud.gateway.filter.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.EncryptBodyUtil;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class EncryptResponseFilterFactory extends AbstractGatewayFilterFactory<EncryptResponseFilterFactory.Config> {
    public EncryptResponseFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new EncryptResponseFilter(config, this);
    }

    @Data
    public static class Config {
        private String publicKey;
        private String privateKey;
        private List<String> whiteList;
        private List<String> blackList;
    }

    public class EncryptResponseFilter implements GatewayFilter, Ordered {
        private final Config config;
        private GatewayFilterFactory<Config> gatewayFilterFactory;

        public EncryptResponseFilter(Config config, GatewayFilterFactory<Config> gatewayFilterFactory) {
            this.config = config;
            this.gatewayFilterFactory = gatewayFilterFactory;
        }

        private boolean shouldEncrypt(ServerWebExchange exchange) {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().toString();

            // 判断白名单和黑名单
            // if (WebFluxUtil.isPathMatch(path, config.getWhiteList())
            // || !WebFluxUtil.isPathMatch(path, config.getBlackList())) {
            // return false;
            // }

            return true;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            if (!shouldEncrypt(exchange)) {
                return chain.filter(exchange);
            }

            return chain.filter(exchange.mutate()
                    .response(new EncryptResponseServerHttpResponse(exchange, this.config))
                    .build());
        }

        @Override
        public int getOrder() {
            return Ordered.HIGHEST_PRECEDENCE;
        }
    }

    @Slf4j
    protected static class EncryptResponseServerHttpResponse extends ServerHttpResponseDecorator {
        private final ServerWebExchange exchange;
        private final Config config;

        private static final ObjectMapper objectMapper = new ObjectMapper();

        public EncryptResponseServerHttpResponse(ServerWebExchange exchange, Config config) {
            super(exchange.getResponse());
            this.exchange = exchange;
            this.config = config;
        }

        @Override
        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
            Flux<DataBuffer> bufferFlux = Flux.from(body);
            Mono<byte[]> data = bufferFlux.collectList().map(dataBuffers -> {
                byte[] array = new byte
                        [dataBuffers.stream()
                                .mapToInt(DataBuffer::readableByteCount)
                                .sum()];
                int position = 0;
                for (DataBuffer dataBuffer : dataBuffers) {
                    int length = dataBuffer.readableByteCount();
                    dataBuffer.read(array, position, length);
                    position += length;
                    DataBufferUtils.release(dataBuffer);
                }
                return array;
            });

            return data.flatMap(bytes -> {
                        String content = new String(bytes, StandardCharsets.UTF_8);
                        try {
                            return Mono.just(EncryptBodyUtil.encrypt(content, this.config.getPublicKey()));
                        } catch (Exception e) {
                            log.error("Failed to encrypt response body", e);
                            return Mono.error(e);
                        }
                    })
                    .flatMap(encrypted -> {
                        try {
                            log.info("Encrypted response body: {}", encrypted);
                            byte[] bytes = objectMapper.writeValueAsBytes(encrypted);
                            DataBuffer buffer = this.bufferFactory().wrap(bytes);
                            getDelegate().getHeaders().setContentLength(bytes.length);
                            return getDelegate().writeWith(Flux.just(buffer));
                        } catch (JsonProcessingException e) {
                            log.error("Failed to serialize encrypted response", e);
                            return Mono.error(e);
                        }
                    })
                    .onErrorResume(e -> {
                        String errorMessage = "Error encrypting response body: " + e.getMessage();
                        log.error(errorMessage, e);
                        DataBuffer buffer = this.bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
                        getDelegate().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                        return getDelegate().writeWith(Flux.just(buffer));
                    });
        }

        @Override
        public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
            return writeWith(Flux.from(body).flatMapSequential(p -> p));
        }
    }
}
