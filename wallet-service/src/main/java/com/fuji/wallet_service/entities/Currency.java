package com.fuji.wallet_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Currency {
    @Id
    private String code;
    private String name;
    private String symbol;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    @OneToMany(mappedBy = "currency")
    private List<Wallet> wallets;
}
