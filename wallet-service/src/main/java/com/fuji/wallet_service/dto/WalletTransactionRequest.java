package com.fuji.wallet_service.dto;

import com.fuji.wallet_service.enums.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WalletTransactionRequest(
        @NotNull(message = "amount required!")
        BigDecimal amount,
        @NotNull(message = "type of transaction required!")
        TransactionType type,
        @NotNull(message = "wallet ID required")
        String walletID
) {
}
