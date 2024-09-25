package com.fuji.wallet_service.services;

import com.fuji.wallet_service.entities.Currency;
import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.repositories.CurrencyRepository;
import com.fuji.wallet_service.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public void loadCurrencies() {
        Path path = Path.of("H:\\currency-manager\\wallet-service\\src\\main\\resources\\currencies.data.csv");


        try {
            Files.readAllLines(path).stream()
                    .filter(line-> !line.startsWith("CODE"))
                    .forEach(line-> {
                        String[] currencyLines = line.split(";");
                        Currency currency= Currency.builder()
                                .code(currencyLines[0])
                                .name(currencyLines[1])
                                .salePrice(new BigDecimal(currencyLines[2]))
                                .purchasePrice(new BigDecimal(currencyLines[3]))
                                .build();

                        currencyRepository.save(currency);
                    });
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error to load the file csv %s", path.getFileName()));
        }

        log.info("data loaded and add to the database successfully");

        Stream.of("AMD", "USD", "CAD", "EUR", "AZN").forEach(currencyCode-> {
            Currency currency= currencyRepository.findById(currencyCode).orElseThrow(
                    ()-> new IllegalArgumentException(String.format("currency %s doesn't exist", currencyCode))
            );

            Random random = new Random();
            Wallet wallet= Wallet.builder()
                    .id(UUID.randomUUID().toString())
                    .currency(currency)
                    .amount(new BigDecimal(random.nextInt(5000, 15000)))
                    .createdAt(new Date())
                    .userID("USER_X")
                    .build();

            walletRepository.save(wallet);
            log.info("wallets added into the database");
        });

    }
}
