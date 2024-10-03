package com.fuji.wallet_service.services;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.entities.Currency;
import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.entities.WalletTransaction;
import com.fuji.wallet_service.enums.TransactionType;
import com.fuji.wallet_service.exception.WalletNotFoundException;
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
        Wallet walletSource= Wallet.builder()
                .id("ID-1")
                .balance(BigDecimal.valueOf(5000))
                .currency(Currency.builder().salePrice(BigDecimal.valueOf(1)).build())
                .build();

        Wallet walletDestination= Wallet.builder()
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
                .wallet(walletSource)
                .build();

        WalletTransactionResponse response= new WalletTransactionResponse(
                12L, new Date(), BigDecimal.valueOf(500), null, null, "ID-2", walletSource, TransactionType.DEBIT
        );

        when(walletTransactionMapper.mapToWalletTransaction(request)).thenReturn(walletTransaction);
        when(walletRepository.findById("ID-1")).thenReturn(Optional.of(walletSource));
        when(walletRepository.findById("ID-2")).thenReturn(Optional.of(walletDestination));
        when(walletTransactionMapper.mapToWalletTransactionResponse(walletTransaction)).thenReturn(response);

        WalletTransactionResponse result = walletTransactionService.proceed(request);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(response);
        assertThat(walletSource.getBalance()).isEqualTo(BigDecimal.valueOf(4500));
        assertThat(walletDestination.getBalance()).isEqualTo(BigDecimal.valueOf(3500));

        verify(walletRepository, times(1)).findById("ID-1");
        verify(walletRepository, times(1)).findById("ID-2");
        verify(walletRepository, times(1)).save(walletSource);
        verify(walletRepository, times(1)).save(walletDestination);
        verify(walletTransactionRepository, times(1)).save(walletTransaction);
        verify(walletTransactionMapper, times(1)).mapToWalletTransaction(request);
        verify(walletTransactionMapper, times(1)).mapToWalletTransactionResponse(walletTransaction);
    }

    @Test
    public void testProceedTransaction_WalletSource_NotFound() {
        final String walletResourceID= "ID-1";
        WalletTransactionRequest request = new WalletTransactionRequest(BigDecimal.valueOf(1000), TransactionType.DEBIT, walletResourceID, null);

        when(walletRepository.findById(request.walletID())).thenReturn(Optional.empty());

        var walletNotFoundException = assertThrows(WalletNotFoundException.class, () -> walletTransactionService.proceed(request));

        assertThat(walletNotFoundException.getMessage()).isEqualTo("no wallet for id "+walletResourceID+" into the database!!!");

        verify(walletRepository, times(1)).findById(walletResourceID);
        verify(walletTransactionMapper, never()).mapToWalletTransaction(any(WalletTransactionRequest.class));
        verify(walletRepository, never()).save(any(Wallet.class));
        verify(walletTransactionRepository, never()).save(any(WalletTransaction.class));
        verify(walletTransactionMapper, never()).mapToWalletTransactionResponse(any(WalletTransaction.class));

    }

    @Test
    public void testProceedTransaction_WalletDestination_NotFound() {
        final String walletSourceID=  "ID-1";
        final String walletDestinationID= "ID-2";

        Wallet walletSource = Wallet.builder()
                .id(walletSourceID)
                .balance(BigDecimal.valueOf(1000))
                .build();

        WalletTransactionRequest request = new WalletTransactionRequest(BigDecimal.valueOf(1000), TransactionType.DEBIT, walletSourceID, walletDestinationID);

        when(walletRepository.findById(walletSourceID)).thenReturn(Optional.of(walletSource));
        when(walletRepository.findById(walletDestinationID)).thenReturn(Optional.empty());

        var walletNotFoundException = assertThrows(WalletNotFoundException.class, () -> walletTransactionService.proceed(request));

        assertThat(walletNotFoundException.getMessage()).isEqualTo("no wallet for id "+walletDestinationID+" into the database!!!");

        verify(walletRepository, times(1)).findById(walletSourceID);
        verify(walletRepository, times(1)).findById(walletDestinationID);
        verify(walletTransactionMapper, never()).mapToWalletTransaction(any(WalletTransactionRequest.class));
        verify(walletRepository, never()).save(any(Wallet.class));
        verify(walletTransactionRepository, never()).save(any(WalletTransaction.class));
        verify(walletTransactionMapper, never()).mapToWalletTransactionResponse(any(WalletTransaction.class));
    }
}