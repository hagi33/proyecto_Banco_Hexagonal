package com.banco_hexagona.account.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


//Importamos las aserciones estáticas para escribir menos codigo
import static org.junit.jupiter.api.Assertions.*;
public class AccountTest {

    @Test
    void deposit_ShouldIncreaseBalance(){
        //GIVEN Damos una cuenta nueva de usuario cualquiera
        Account account = Account.createdWithoutId(1L);

        //When Ingresamos 100 euros
        account.deposit(new BigDecimal(100));

        //Then el saldo debe ser 100
        assertEquals(new BigDecimal(100), account.getBalance());
    }

    @Test
    void withdraw_WithSufficienteFunds_ShouldDecreaseBalance(){
        //GIVEN
        Account account = Account.createdWithoutId(1L);
        account.deposit(new BigDecimal(100));

        //WHEN
        boolean succes = account.withdraw(new BigDecimal(40));

        //THEN
        assertTrue(succes,"El retiro deberia ser exitoso");
        assertEquals(new BigDecimal(60), account.getBalance());

    }

    @Test
    void withdraw_WithInsufficientFunds_ShouldFail(){
        //GIVEN una cuenta con 50 euros
        Account account = Account.createdWithoutId(1L);
        account.deposit(new BigDecimal(50));

        //WHEN
        boolean succes = account.withdraw(new BigDecimal(100));

        //THEN
        assertFalse(succes, "El retiro no debería permitirse");
        assertEquals(new BigDecimal(50), account.getBalance());

    }

    @Test
    void depossit_NegativeAmount_ShouldThrowException(){
        Account account = Account.createdWithoutId(1L);

        //Verificamos que lance una excepción si intentamos meter dinero negativo
        assertThrows(IllegalArgumentException.class, () -> {
                account.deposit(new BigDecimal(-10.00));
        });

    }

}
