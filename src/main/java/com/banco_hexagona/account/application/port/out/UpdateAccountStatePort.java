package com.banco_hexagona.account.application.port.out;

import com.banco_hexagona.account.domain.Account;

public interface UpdateAccountStatePort {


    //Con este metodo el dominio guarda los cambios hechos en la cuenta
    void updateAccountState(Account account);
}
