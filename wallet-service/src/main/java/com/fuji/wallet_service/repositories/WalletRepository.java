package com.fuji.wallet_service.repositories;

import com.fuji.wallet_service.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {
}
