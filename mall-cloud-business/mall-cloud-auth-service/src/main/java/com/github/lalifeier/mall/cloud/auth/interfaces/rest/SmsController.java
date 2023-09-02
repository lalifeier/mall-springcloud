package com.github.lalifeier.mall.cloud.auth.interfaces.rest;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
  @PostMapping("/sendSmsCode")
  public void sendSmsCode(HttpSession session, @RequestParam String phoneNumber) {}
}
