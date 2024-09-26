package com.fuji.wallet_service.dto;

import com.fuji.wallet_service.entities.Currency;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WalletRequest(
    @NotNull(message = "amount required")
    BigDecimal balance,
    @NotNull(message = "ID of user required")
    String userID,
    @NotNull(message = "currency of the wallet required")
    String currencyCode
) {
}
