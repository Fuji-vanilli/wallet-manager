package com.fuji.wallet_service.services;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletResponse;
import com.fuji.wallet_service.entities.Currency;
import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.exception.CurrencyNotFoundException;
import com.fuji.wallet_service.mapper.WalletMapper;
import com.fuji.wallet_service.repositories.CurrencyRepository;
import com.fuji.wallet_service.repositories.WalletRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WalletServiceTest {

    @InjectMocks
    private WalletServiceImpl walletService;
    @Mock
    private WalletMapper walletMapper;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private CurrencyRepository currencyRepository;

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
    public void testAddWallet_success() {
        final WalletRequest request= new WalletRequest(BigDecimal.valueOf(1000), "USER-1", "EUR");
        final Wallet wallet= Wallet.builder()
                .id(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(1000))
                .userID("USER-1")
                .build();
        final Currency currency= Currency.builder()
                .code("EUR")
                .name("euro")
                .salePrice(BigDecimal.valueOf(1))
                .purchasePrice(BigDecimal.valueOf(1))
                .build();

        WalletResponse expectedResponse= new WalletResponse(wallet.getId(), BigDecimal.valueOf(1000), currency, "USER-1", null);

        when(walletMapper.mapToWallet(request)).thenReturn(wallet);
        when(currencyRepository.findByCode("EUR")).thenReturn(Optional.of(currency));
        when(walletRepository.save(wallet)).thenReturn(wallet);
        when(walletMapper.mapToWalletResponse(any(Wallet.class))).thenReturn(expectedResponse);

        WalletResponse actualResponse = walletService.add(request);

        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse).isEqualTo(expectedResponse);

        verify(currencyRepository, times(1)).findByCode("EUR");
        verify(walletMapper, times(1)).mapToWallet(request);
        verify(walletMapper, times(1)).mapToWalletResponse(wallet);
    }

    @Test
    public void testAddWallet_currencyNotFound() {
        final WalletRequest request= new WalletRequest(BigDecimal.valueOf(1000), "USER-1", "EUR");
        final Wallet wallet= Wallet.builder()
                .id(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(1000))
                .userID("USER-1")
                .build();

        when(walletMapper.mapToWallet(request)).thenReturn(wallet);
        when(currencyRepository.findByCode("EUR")).thenReturn(Optional.empty());

        var currencyNotFoundException = assertThrows(CurrencyNotFoundException.class, () -> walletService.add(request));
        assertThat(currencyNotFoundException.getMessage()).isEqualTo("No currency with code EUR");

        verify(walletMapper, times(1)).mapToWallet(request);
        verify(currencyRepository, times(1)).findByCode("EUR");
        verify(walletRepository, never()).save(any(Wallet.class));

    }

    @Test
    public void shouldGetAllWallet() {
        Wallet wallet1= Wallet.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(BigDecimal.valueOf(1000))
                .userID("USER-1")
                .build();

        Wallet wallet2= Wallet.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(BigDecimal.valueOf(3000))
                .userID("USER-2")
                .build();

        when(walletRepository.findAll()).thenReturn(Arrays.asList(wallet1, wallet2));

        WalletResponse response1 = new WalletResponse(wallet1.toString(), null, null, "USER-1", null);
        WalletResponse response2 = new WalletResponse(wallet2.toString(), null, null, "USER-2", null);

        when(walletMapper.mapToWalletResponse(wallet1)).thenReturn(response1);
        when(walletMapper.mapToWalletResponse(wallet2)).thenReturn(response2);

        List<WalletResponse> wallets = walletService.allWallet();

        assertThat(wallets.size()).isEqualTo(2);
        assertThat(wallets.get(0)).isEqualTo(response1);
        assertThat(wallets.get(1)).isEqualTo(response2);

        verify(walletRepository, times(1)).findAll();
        verify(walletMapper, times(1)).mapToWalletResponse(wallet1);
        verify(walletMapper, times(1)).mapToWalletResponse(wallet2);
    }
}