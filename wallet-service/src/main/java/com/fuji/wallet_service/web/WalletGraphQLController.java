package com.fuji.wallet_service.web;

import com.fuji.wallet_service.entities.Wallet;
import com.fuji.wallet_service.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WalletGraphQLController {
    private final WalletService walletService;

    @QueryMapping
    public List<Wallet> userWallets() {
        return walletService.allWallet();
    }
}
