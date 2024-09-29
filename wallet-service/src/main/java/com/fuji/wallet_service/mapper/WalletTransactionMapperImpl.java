package com.fuji.wallet_service.mapper;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.entities.WalletTransaction;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletTransactionMapperImpl implements WalletTransactionMapper{
    @Override
    public WalletTransaction mapToWalletTransaction(WalletTransactionRequest request) {
        return WalletTransaction.builder()
                .amount(request.amount())
                .type(request.type())
                .walletDestinationID(request.walletDestinationID())
                .build();
    }

    @Override
    public WalletTransactionResponse mapToWalletTransactionResponse(WalletTransaction walletTransaction) {
        return new WalletTransactionResponse(
                walletTransaction.getId(),
                walletTransaction.getTimestamp(),
                walletTransaction.getAmount(),
                walletTransaction.getSaleCurrencyPrice(),
                walletTransaction.getPurchaseCurrencyPrice(),
                walletTransaction.getWallet(),
                walletTransaction.getType()
        );
    }
}
