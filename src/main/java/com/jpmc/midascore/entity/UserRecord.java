package com.vagabond.forgemidas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_records")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException(
                "Insufficient funds for user: " + userId +
                ". Balance: " + balance + ", Requested: " + amount
            );
        }
        this.balance = this.balance.subtract(amount);
    }
}