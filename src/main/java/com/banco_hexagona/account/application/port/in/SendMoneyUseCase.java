package com.banco_hexagona.account.application.port.in;

import java.math.BigDecimal;

public interface SendMoneyUseCase {


    boolean sendMoney(Long sourceAccountId, Long targetAccountId, BigDecimal amount);


}
