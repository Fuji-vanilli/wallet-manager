package com.fuji.wallet_service.services;

import com.fuji.wallet_service.dto.WalletResponse;
import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;

import java.util.Date;
import java.util.List;

public interface WalletTransactionService {
    WalletTransactionResponse proceed(WalletTransactionRequest request);
    WalletTransactionResponse getById(Long id);
    List<WalletTransactionResponse> getAllByDate(Date date);
    List<WalletTransactionResponse> getAll();
}
