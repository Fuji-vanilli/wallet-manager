package com.fuji.wallet_service.services;

import com.fuji.wallet_service.mapper.WalletMapper;
import com.fuji.wallet_service.repositories.WalletRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;
    @Mock
    private WalletMapper walletMapper;
    @Mock
    private WalletRepository walletRepository;

    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
}