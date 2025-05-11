package com.org.services;

import com.org.converters.account.AccountDtoToEntityMapper;
import com.org.dto.AccountDto;
import com.org.entities.AccountEntity;
import com.org.entities.UserEntity;
import com.org.exceptions.AccountNotFoundException;
import com.org.exceptions.NotEnoughMoneyException;
import com.org.repository.AccountRepository;
import com.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoToEntityMapper accountDtoToEntityMapper;
    private final UserRepository userRepository;

    @Override
    public void create(AccountDto accountDto) {
        AccountEntity entity = accountDtoToEntityMapper
                .mapToEntity(accountDto);

        entity.setBalance(entity.getInitialDeposit());
        entity.setId(UUID.randomUUID());

        UserEntity userEntity = userRepository.getUserById(accountDto.userId());

        entity.setUser(userEntity);

        accountRepository.save(entity);
    }

    @Override
    public AccountDto getAccountByUserId(UUID id) {
        var account = accountRepository.findByUserId(id)
                .orElseThrow(() -> new AccountNotFoundException(String
                        .format("account with user id %s not found", id)));

        return accountDtoToEntityMapper
                .mapToDto(account);
    }

    @Override
    public void transferMoney(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        boolean success = accountRepository.decreaseBalance(fromAccountId, amount);
        if (!success) {
            throw new NotEnoughMoneyException(String
                    .format("there are not enough funds in the account with id %s",fromAccountId));
        }
        accountRepository.increaseBalance(toAccountId, amount);
    }
}
