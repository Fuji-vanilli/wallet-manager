package com.fuji.wallet_service.mapper;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.entities.WalletTransaction;

public interface WalletTransactionMapper {
    WalletTransaction mapToWalletTransaction(WalletTransactionRequest request);
    WalletTransactionResponse mapToWalletTransactionResponse(WalletTransaction walletTransaction);
}
