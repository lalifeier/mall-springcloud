package com.github.lalifeier.mall.cloud.signature.exception;

import org.springframework.security.web.csrf.CsrfException;

public class InvalidRequestTimeOrNonceException extends CsrfException {
    public InvalidRequestTimeOrNonceException(String message) {
        super("Invalid timestamp or Nonce :" + message);
    }
}
