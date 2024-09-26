package com.fuji.wallet_service.web;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletResponse;
import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WalletGraphQLController implements WalletGraphQLAPI {
    private final WalletService walletService;

    @Override
    public List<WalletResponse> userWallets() {
        return walletService.allWallet();
    }

    @Override
    public WalletResponse walletById(String id) {
        return walletService.getById(id);
    }

    @Override
    public WalletResponse addWallet(WalletRequest request) {
        return walletService.add(request);
    }

}
