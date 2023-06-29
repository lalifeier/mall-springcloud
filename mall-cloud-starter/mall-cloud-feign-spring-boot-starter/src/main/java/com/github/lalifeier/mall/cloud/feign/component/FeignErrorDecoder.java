package com.github.lalifeier.mall.cloud.feign.component;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.charset.Charset;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {
  //  private final ObjectMapper objectMapper;
  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      Reader reader = response.body().asReader(Charset.defaultCharset());
//      Result<?> result = objectMapper.readValue(reader, Result.class);
//      return new BusinessException(result.getCode(), result.getMessage());
    } catch (Exception e) {
      log.error("Response 转换异常: ", e);
//      return new BusinessException(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
    }
    return decode(methodKey, response);
  }
}
