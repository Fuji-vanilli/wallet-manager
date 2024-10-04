package com.fuji.wallet_service.web;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import com.fuji.wallet_service.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WalletTransactionGraphQLController implements WalletTransactionGraphQLAPI {
    private final WalletTransactionService walletTransactionService;
    @Override
    public WalletTransactionResponse proceed(WalletTransactionRequest request) {
        return walletTransactionService.proceed(request);
    }

    @Override
    public WalletTransactionResponse getById(Long id) {
        return walletTransactionService.getById(id);
    }

    @Override
    public List<WalletTransactionResponse> getAll() {
        return walletTransactionService.getAll();
    }
}
