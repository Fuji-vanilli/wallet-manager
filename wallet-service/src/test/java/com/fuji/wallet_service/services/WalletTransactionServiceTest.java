package com.fuji.wallet_service.services;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.entities.Currency;
import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.entities.WalletTransaction;
import com.fuji.wallet_service.enums.TransactionType;
import com.fuji.wallet_service.mapper.WalletMapper;
import com.fuji.wallet_service.mapper.WalletTransactionMapper;
import com.fuji.wallet_service.repositories.WalletRepository;
import com.fuji.wallet_service.repositories.WalletTransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WalletTransactionServiceTest {

    @InjectMocks
    private WalletTransactionServiceImpl walletTransactionService;
    @Mock
    private WalletTransactionRepository walletTransactionRepository;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private WalletTransactionMapper walletTransactionMapper;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testProceedTransaction_success() {
        Wallet wallet1= Wallet.builder()
                .id("ID-1")
                .balance(BigDecimal.valueOf(5000))
                .currency(Currency.builder().salePrice(BigDecimal.valueOf(1)).build())
                .build();

        Wallet wallet2= Wallet.builder()
                .id("ID-2")
                .balance(BigDecimal.valueOf(3000))
                .currency(Currency.builder().purchasePrice(BigDecimal.valueOf(1)).build())
                .build();

        WalletTransactionRequest request= new WalletTransactionRequest(
                BigDecimal.valueOf(500),
                TransactionType.DEBIT,
                "ID-1",
                "ID-2");

        WalletTransaction walletTransaction= WalletTransaction.builder()
                .id(12L)
                .type(TransactionType.DEBIT)
                .amount(BigDecimal.valueOf(500))
                .walletDestinationID("ID-2")
                .wallet(wallet1)
                .build();

        WalletTransactionResponse response= new WalletTransactionResponse(
                12L, new Date(), BigDecimal.valueOf(500), null, null, "ID-2", wallet1, TransactionType.DEBIT
        );

        when(walletTransactionMapper.mapToWalletTransaction(request)).thenReturn(walletTransaction);
        when(walletRepository.findById("ID-1")).thenReturn(Optional.of(wallet1));
        when(walletRepository.findById("ID-2")).thenReturn(Optional.of(wallet2));

        WalletTransactionResponse result = walletTransactionService.proceed(request);

        assertThat(result).isEqualTo(response);
        assertThat(wallet1.getBalance()).isEqualTo(BigDecimal.valueOf(4500));
        assertThat(wallet2.getBalance()).isEqualTo(BigDecimal.valueOf(3500));

        verify(walletRepository, times(1)).findById("ID-1");
        verify(walletRepository, times(1)).findById("ID-2");
        verify(walletTransactionMapper, times(1)).mapToWalletTransaction(request);
        verify(walletTransactionMapper, times(1)).mapToWalletTransactionResponse(walletTransaction);
    }


}