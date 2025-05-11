package com.org.services;

import com.org.converters.transaction.TransactionDtoToEntityMapper;
import com.org.dto.TransactionDto;
import com.org.exceptions.AccountNotFoundException;
import com.org.repository.AccountRepository;
import com.org.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDtoToEntityMapper transactionDtoToEntityMapper;
    private final AccountRepository accountRepository;


    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        log.debug("trying to create transaction {}", transactionDto);
        var entity = transactionDtoToEntityMapper.mapDtoToEntity(transactionDto);

        entity.setId(UUID.randomUUID());

        entity.setToAccount(accountRepository.findByUserId(transactionDto.toAccountId())
                .orElseThrow(() -> new AccountNotFoundException(String
                        .format("account with user id %s not found", transactionDto.toAccountId()))));

        entity.setFromAccount(accountRepository.findByUserId(transactionDto.toAccountId())
                .orElseThrow(() -> new AccountNotFoundException(String
                        .format("account with user id %s not found", transactionDto.toAccountId()))));

        var result = transactionRepository.save(entity);

        log.debug("created transaction {}", result);
        return transactionDtoToEntityMapper.mapEntityToDto(result);
    }
}
