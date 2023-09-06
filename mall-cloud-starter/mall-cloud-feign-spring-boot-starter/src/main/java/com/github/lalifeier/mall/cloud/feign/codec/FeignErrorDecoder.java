package com.github.lalifeier.mall.cloud.feign.codec;

import com.github.lalifeier.mall.cloud.common.exception.BusinessException;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        int statusCode = response.status();
        String reason = response.reason();

        log.error("Request failed with status code {}: {}", statusCode, reason);

        try {
            Reader reader = response.body().asReader(Charset.defaultCharset());
            Result<?> result = new Gson().fromJson(reader, Result.class);
            throw new BusinessException(result.getMessage());
        } catch (Exception e) {
            log.error("Failed to parse response as Result: ", e);
            return new Exception(e.getMessage());
        }
    }

    private boolean isResultResponse(Response response) {
        try {
            Reader reader = response.body().asReader(Charset.defaultCharset());
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            return json.has("error");
        } catch (Exception e) {
            return false;
        }
    }
}
