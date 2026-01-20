package com.banco_hexagona.account.application.service;

import com.banco_hexagona.account.application.port.in.SendMoneyUseCase;
import com.banco_hexagona.account.application.port.out.LoadAccountPort;
import com.banco_hexagona.account.application.port.out.UpdateAccountStatePort;
import com.banco_hexagona.account.domain.Account;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service //Le dice a Spring que gestione la clase
@Transactional //Esta anotacion se encarga de que todo ocurra o que no ocurra nada (Atomicidad)
public class SendMoneyService implements SendMoneyUseCase {


    //Dependencias: Necesitamos los puertos de salida para hablar con la BBDD
    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    //Inyección de dependencias por constructor
    public SendMoneyService(LoadAccountPort loadAccountPort, UpdateAccountStatePort updateAccountStatePort) {
        this.loadAccountPort = loadAccountPort;
        this.updateAccountStatePort = updateAccountStatePort;
    }


    @Override
    public boolean sendMoney(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        //1. cargar los datos usuando el puerto de salida
        //En un caso real debemos lanzar una excepcion si no existen las cuentas
        //En este caso simplificamos devolviendo false si falta alguna cuenta

        Account sourceAccount = loadAccountPort.loadAccount(sourceAccountId)
                .orElse(null);
        Account targeAccount = loadAccountPort.loadAccount(targetAccountId)
                .orElse(null);

        if (sourceAccountId == null || targeAccount == null){
            return false;
        }

        // 2. Validadmos y modificamos usando el dominio
        // La lógica está en la entidad Account, no aquí
        // El servicio solo coordina

        if (!sourceAccount.withdraw(amount)){
            return false; //saldo insuficiente
        }

        targeAccount.deposit(amount);

        // 3. Guardar estado usando el puerto de salidad
        updateAccountStatePort.updateAccountState(sourceAccount);
        updateAccountStatePort.updateAccountState(targeAccount);

        return true;

    }
}
