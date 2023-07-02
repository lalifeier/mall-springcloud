package com.github.lalifeier.mall.cloud.feign.codec;

import com.github.lalifeier.mall.cloud.common.model.result.Result;
import com.google.gson.Gson;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

@Slf4j
@Component
public class FeignDecoder implements Decoder {

  private final Decoder decoder;

  public FeignDecoder(Decoder decoder) {
    this.decoder = decoder;
  }

  @Override
  public Object decode(Response response, Type type) throws IOException, FeignException {
    if (response.body() == null) {
      return null;
    }

    if (isResultType(type)) {
      Type dataType = getDataType(type);

      Result<Object> result = parseResponse(response, dataType);

      if (result.isSuccess()) {
        return result.getData();
      }

      throw new RuntimeException(result.getErrMessage());
    }

    return decoder.decode(response, type);

  }

  private boolean isResultType(Type type) {
    if (type instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) type;
      if (parameterizedType.getRawType().equals(Result.class)) {
        return true;
      }
    }
    return false;
  }

  private Type getDataType(Type type) {
    ParameterizedType parameterizedType = (ParameterizedType) type;
    return parameterizedType.getActualTypeArguments()[0];
  }

  private Result<Object> parseResponse(Response response, Type dataType) throws IOException {
    Reader reader = response.body().asReader(Charset.defaultCharset());
    return new Gson().fromJson(reader, dataType);
  }
}
