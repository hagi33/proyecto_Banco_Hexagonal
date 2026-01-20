package com.banco_hexagona.account.application.port.out;

import com.banco_hexagona.account.domain.Account;

import java.util.Optional;

public interface LoadAccountPort {

    //El dominio dice: necesito una forma de encontrar la cuenta, le damos el id.
    Optional<Account> loadAccount(Long id);
}


