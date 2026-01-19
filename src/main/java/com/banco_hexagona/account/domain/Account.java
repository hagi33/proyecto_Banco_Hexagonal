package com.banco_hexagona.account.domain;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {

    private final Long id;
    private final Long userId;
    private BigDecimal balance;
    private final LocalDateTime createdAt;


    //metodo para crear una cuenta nueva
    public static Account createdWithoutId(Long userId){
        return new Account(null, userId, BigDecimal.ZERO, LocalDateTime.now());
    }

    //metodo para crear cuenta desde la base de datos
    public static Account withId(Long id, Long userId, BigDecimal balance, LocalDateTime createdAt){
        return new Account(id, userId,balance,createdAt);
    }


    public Account(Long id, Long userId, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
    }


    //Lógica de negocio

    /**
     * Intenta retirar dinero.
     * @return true si la operación fue exitosa, false si no hay saldo suficiente
     * */
    public boolean withdraw(BigDecimal amount){
        validateAmount(amount);

        //Si el saldo es menor que la cantidad a retirar, da false
        if (this.balance.compareTo(amount) < 0){
            return false;
        }

        this.balance = this.balance.subtract(amount);
        return true;
    }

    /**
     * Ingresa dinero en la cuenta
     * */
    public void deposit(BigDecimal amount){
        validateAmount(amount);
        this.balance = this.balance.add(amount);

    }

    //metodo de validacion auxiliar, no permitimos ni ceros ni negativos
    private void validateAmount(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw  new IllegalArgumentException("El monto debe ser positivo");

        }
    }

    //Getters y setters SOLO LECTURA

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
