package com.fuji.wallet_service.web;

import com.fuji.wallet_service.dto.WalletRequest;
import com.fuji.wallet_service.dto.WalletResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

public interface WalletGraphQLAPI {
    @QueryMapping
    List<WalletResponse> userWallets();
    @QueryMapping
    WalletResponse walletById(@Argument String id);
    @MutationMapping
    WalletResponse addWallet(@Argument WalletRequest request);

}
