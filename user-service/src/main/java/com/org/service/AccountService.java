package com.org.service;

import com.org.dto.AccountDto;

import java.util.UUID;

public interface AccountService {
    void create(AccountDto accountDto);

    AccountDto getAccountByUserId(UUID id);


}
