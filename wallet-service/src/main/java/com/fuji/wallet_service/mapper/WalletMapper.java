package com.fuji.wallet_service.mapper;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletResponse;
import com.fuji.wallet_service.entities.Wallet;

public interface WalletMapper {
    Wallet mapToWallet(WalletRequest request);
    WalletResponse mapToWalletResponse(Wallet wallet);
}
