package com.fuji.wallet_service.dto;

import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public record WalletTransactionResponse(
        Long id,
        Date timestamp,
        BigDecimal amount,
        BigDecimal saleCurrencyPrice,
        BigDecimal purchaseCurrencyPrice,
        String walletDestinationID,
        Wallet wallet,
        TransactionType type
) {
}
