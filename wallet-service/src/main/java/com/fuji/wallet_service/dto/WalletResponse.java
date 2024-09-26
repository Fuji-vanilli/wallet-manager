package com.fuji.wallet_service.dto;

import com.fuji.wallet_service.entities.Currency;
import com.fuji.wallet_service.entities.WalletTransaction;

import java.math.BigDecimal;
import java.util.List;

public record WalletResponse (
        String id,
        BigDecimal balance,
        Currency currency,
        String userID,
        List<WalletTransaction> walletTransactions

){
}
