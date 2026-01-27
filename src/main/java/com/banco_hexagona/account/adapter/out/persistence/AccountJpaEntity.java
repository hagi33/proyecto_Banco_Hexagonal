package com.banco_hexagona.account.adapter.out.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="account") //Nombre de la talbla en MySQL
public class AccountJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private BigDecimal balance;
    private LocalDateTime createdAt;


    //Obligatorio el constructor vacio para el funcionamiento del JPA
    public AccountJpaEntity() {
    }

    //Constructor completo para facilitar la conversión
    public AccountJpaEntity(Long id, Long userId, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
