package com.fuji.wallet_service.web;

import com.fuji.wallet_service.dto.WalletTransactionRequest;
import com.fuji.wallet_service.dto.WalletTransactionResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

public interface WalletTransactionGraphQLAPI {
    @MutationMapping
    WalletTransactionResponse proceed(@Argument WalletTransactionRequest request);
    @QueryMapping
    WalletTransactionResponse getById(@Argument Long id);
    @QueryMapping
    List<WalletTransactionResponse> getAll();
}
