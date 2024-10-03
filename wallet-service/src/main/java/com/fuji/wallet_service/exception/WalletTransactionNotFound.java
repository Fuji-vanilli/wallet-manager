package com.fuji.wallet_service.exception;

public class WalletTransactionNotFound extends RuntimeException{
    private String message;

    public WalletTransactionNotFound(String message) {
        super(message);
    }

    public WalletTransactionNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
