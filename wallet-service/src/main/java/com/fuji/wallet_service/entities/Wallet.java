package com.fuji.wallet_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Wallet {
    @Id
    private String id;
    private BigDecimal balance;
    private Date createdAt;
    private String userID;
    @ManyToOne
    private Currency currency;
    @OneToMany(mappedBy = "wallet")
    private List<WalletTransaction> walletTransactions;
}
