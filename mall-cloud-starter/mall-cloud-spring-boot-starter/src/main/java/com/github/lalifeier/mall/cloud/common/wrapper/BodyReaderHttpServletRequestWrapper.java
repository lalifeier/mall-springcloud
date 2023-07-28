package com.github.lalifeier.mall.cloud.common.wrapper;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private final byte[] requestBody;

  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    requestBody = StreamUtils.copyToByteArray(request.getInputStream());
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {

    final ByteArrayInputStream basic = new ByteArrayInputStream(requestBody);

    return new ServletInputStream() {

      @Override
      public int read() throws IOException {
        return basic.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }
    };
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }
}
