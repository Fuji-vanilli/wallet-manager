package com.fuji.wallet_service.repositories;

import com.fuji.wallet_service.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
    Optional<Currency> findByCode(String code);
}
