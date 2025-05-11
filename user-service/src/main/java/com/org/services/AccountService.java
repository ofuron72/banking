package com.org.services;

import com.org.dto.AccountDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    void create(AccountDto accountDto);

    AccountDto getAccountByUserId(UUID id);

    void transferMoney(UUID fromAccountId, UUID toAccountId, BigDecimal amount);


}
