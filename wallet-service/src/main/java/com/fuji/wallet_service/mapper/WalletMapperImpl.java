package com.fuji.wallet_service.mapper;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletResponse;
import com.fuji.wallet_service.entities.Wallet;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletMapperImpl implements WalletMapper {
    @Override
    public Wallet mapToWallet(WalletRequest request) {
        return Wallet.builder()
                .balance(request.balance())
                .currency(request.currency())
                .userID(request.userID())
                .build();
    }

    @Override
    public WalletResponse mapToWalletResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getBalance(),
                wallet.getCurrency(),
                wallet.getUserID(),
                wallet.getWalletTransactions()
        );
    }
}
