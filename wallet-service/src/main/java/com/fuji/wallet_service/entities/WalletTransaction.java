package com.fuji.wallet_service.entities;

import com.fuji.wallet_service.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Date timestamp;
    private BigDecimal saleCurrencyPrice;
    private BigDecimal purchaseCurrencyPrice;
    @ManyToOne
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
