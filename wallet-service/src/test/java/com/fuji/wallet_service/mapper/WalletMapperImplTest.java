package com.fuji.wallet_service.mapper;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletResponse;
import com.fuji.wallet_service.entities.Currency;
import com.fuji.wallet_service.entities.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


class WalletMapperImplTest {

    private WalletMapper mapper;

    @BeforeEach
    public void setup() {
        mapper= new WalletMapperImpl();
    }

    @Test
    public void shouldMapWalletRequestToWallet() {
        final WalletRequest request= new WalletRequest(new BigDecimal(5000), "USER-X", "EUR");

        Wallet wallet = mapper.mapToWallet(request);

        assertThat(wallet.getBalance()).isEqualTo(request.balance());
        assertThat(wallet.getUserID()).isEqualTo(request.userID());
        assertThat(request.currencyCode()).isUpperCase();
    }

    @Test
    public void shouldMapWalletToWalletResponse() {
        final Currency currency= Currency.builder()
                .code("EUR")
                .name("euro")
                .purchasePrice(new BigDecimal("1.25"))
                .salePrice(new BigDecimal("1.01"))
                .build();

        final Wallet wallet= Wallet.builder()
                .id("W-1")
                .userID("USER-1")
                .balance(new BigDecimal(1000))
                .createdAt(new Date())
                .currency(currency)
                .walletTransactions(new ArrayList<>())
                .build();

        WalletResponse walletResponse = mapper.mapToWalletResponse(wallet);

        assertThat(wallet.getBalance()).isEqualTo(walletResponse.balance());
        assertThat(wallet.getUserID()).isEqualTo(walletResponse.userID());
        assertThat(wallet.getCurrency()).isEqualTo(walletResponse.currency());
        assertThat(wallet.getId()).isEqualTo(walletResponse.id());
    }
}