package com.org.service;

import com.org.converters.AccountDtoToEntityMapper;
import com.org.dto.AccountDto;
import com.org.entities.AccountEntity;
import com.org.entities.UserEntity;
import com.org.repository.AccountRepository;
import com.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
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
        return accountDtoToEntityMapper
                .mapToDto(accountRepository.findByUserId(id));
    }



}
