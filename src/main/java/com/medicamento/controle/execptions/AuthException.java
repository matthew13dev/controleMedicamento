package com.medicamento.controle.execptions;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
