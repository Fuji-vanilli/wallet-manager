package com.fuji.wallet_service.services;

import com.fuji.wallet_service.entities.Wallet;

import java.util.List;

public interface WalletService {
    void loadCurrencies();
    List<Wallet> allWallet();
}
