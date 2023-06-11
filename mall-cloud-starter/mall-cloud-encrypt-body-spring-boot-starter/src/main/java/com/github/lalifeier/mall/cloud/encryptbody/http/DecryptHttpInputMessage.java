package com.github.lalifeier.mall.cloud.encryptbody.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

public class DecryptHttpInputMessage implements HttpInputMessage {

  private final InputStream body;
  private final HttpHeaders headers;

  public DecryptHttpInputMessage(InputStream body, HttpHeaders headers) {
    this.body = body;
    this.headers = headers;
  }


  @Override
  public InputStream getBody() throws IOException {
    return body;
  }

  @Override
  public HttpHeaders getHeaders() {
    return headers;
  }
}
