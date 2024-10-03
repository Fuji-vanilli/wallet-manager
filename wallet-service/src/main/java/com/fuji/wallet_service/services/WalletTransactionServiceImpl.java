package com.fuji.wallet_service.services;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.entities.WalletTransaction;
import com.fuji.wallet_service.exception.WalletNotFoundException;
import com.fuji.wallet_service.mapper.WalletTransactionMapper;
import com.fuji.wallet_service.repositories.WalletRepository;
import com.fuji.wallet_service.repositories.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WalletTransactionServiceImpl implements WalletTransactionService{
    private final WalletTransactionRepository walletTransactionRepository;
    private final WalletRepository walletRepository;
    private final WalletTransactionMapper walletTransactionMapper;

    @Override
    public WalletTransactionResponse proceed(WalletTransactionRequest request) {
        Wallet walletSource = walletRepository.findById(request.walletID()).orElseThrow(
                () -> new WalletNotFoundException(String.format("no wallet for id %s into the database!!!", request.walletID()))
        );

        Wallet walletDestination = walletRepository.findById(request.walletDestinationID()).orElseThrow(
                () -> new WalletNotFoundException(String.format("no wallet for id %s into the database!!!", request.walletDestinationID()))
        );

        BigDecimal updateBalanceSource = walletSource.getBalance().subtract(request.amount());
        walletSource.setBalance(updateBalanceSource);

        BigDecimal updateBalanceDestination= walletDestination.getBalance().add(request.amount()
                .multiply(walletSource.getCurrency().getSalePrice().divide(walletDestination.getCurrency().getPurchasePrice(), RoundingMode.HALF_UP)));
        walletDestination.setBalance(updateBalanceDestination);

        walletRepository.save(walletSource);
        walletRepository.save(walletDestination);

        WalletTransaction walletTransaction = walletTransactionMapper.mapToWalletTransaction(request);
        walletTransaction.setWallet(walletSource);
        walletTransaction.setTimestamp(new Date());
        walletTransaction.setPurchaseCurrencyPrice(walletSource.getCurrency().getPurchasePrice());
        walletTransaction.setSaleCurrencyPrice(walletSource.getCurrency().getSalePrice());

        walletTransactionRepository.save(walletTransaction);

        log.info("transaction proceed successfully!");
        return walletTransactionMapper.mapToWalletTransactionResponse(walletTransaction);
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
