package com.fuji.wallet_service.services;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.mapper.WalletTransactionMapper;
import com.fuji.wallet_service.repositories.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WalletTransactionServiceImpl implements WalletTransactionService{
    private final WalletTransactionRepository walletTransactionRepository;
    private final WalletTransactionMapper walletTransactionMapper;

    @Override
    public WalletTransactionResponse proceed(WalletTransactionRequest request) {

        return null;
    }

    @Override
    public WalletTransactionResponse getById(Long id) {
        return null;
    }

    @Override
    public List<WalletTransactionResponse> getAllByDate(Date date) {
        return List.of();
    }

    @Override
    public List<WalletTransactionResponse> getAll() {
        return List.of();
    }
}
