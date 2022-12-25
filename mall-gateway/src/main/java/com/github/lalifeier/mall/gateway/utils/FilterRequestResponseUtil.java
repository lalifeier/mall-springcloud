package com.github.lalifeier.mall.gateway.utils;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public final class FilterRequestResponseUtil {

    /**
     * 读取body内容
     *
     * @param body
     * @return
     */
    public static String resolveBodyFromRequest(Flux<DataBuffer> body) {
        AtomicReference<String> bodyRef = new AtomicReference<>();
        // 缓存读取的request body信息
        body.subscribe(dataBuffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
            DataBufferUtils.release(dataBuffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
