package com.org.services;

import com.org.converters.account.AccountDtoToEntityMapper;
import com.org.dto.AccountDto;
import com.org.repository.AccountRepository;
import com.org.exceptions.NotEnoughMoneyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoToEntityMapper accountDtoToEntityMapper;

    @Override
    public void create(AccountDto accountDto) {
        log.debug("Creating account {}", accountDto);
        var entity = accountDtoToEntityMapper
                .mapToEntity(accountDto);

        entity.setId(UUID.randomUUID());
        entity.setBalance(accountDto.initialDeposit());

        accountRepository.save(entity);
        log.debug("Created account {}", entity);
    }
    @Override
    public void transferMoney(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        log.debug("trying transfer money from {} to {}", fromAccountId, toAccountId);
        boolean success = accountRepository.decreaseBalance(fromAccountId, amount);
        if (!success) {
            throw new NotEnoughMoneyException(String
                    .format("there are not enough funds in the account with id %s",fromAccountId));
        }

        accountRepository.increaseBalance(toAccountId, amount);
        log.debug("Transferred money from {} to {}", fromAccountId, toAccountId);
    }

}
