package com.github.lalifeier.exception;

import org.springframework.security.web.csrf.CsrfException;

public class InvalidSignatureException extends CsrfException {
    public InvalidSignatureException(String message) {
        super("Invalid Signature :" + message);
    }
}
