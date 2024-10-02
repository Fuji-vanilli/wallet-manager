package com.fuji.wallet_service.services;

import com.fuji.wallet_service.mapper.WalletMapper;
import com.fuji.wallet_service.repositories.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class WalletTransactionServiceTest {

    @InjectMocks
    private WalletTransactionServiceImpl walletTransactionService;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private WalletMapper walletMapper;
    @BeforeEach
    void setUp() {
    }
}