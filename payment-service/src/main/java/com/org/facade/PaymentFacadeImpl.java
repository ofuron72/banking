package com.org.facade;

import com.org.converters.transaction.TransactionDtoToResponseMapper;
import com.org.converters.transaction.TransactionRequestToDtoMapper;
import com.org.dto.TransactionRequestDto;
import com.org.dto.TransactionResponseDto;
import com.org.services.AccountService;
import com.org.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TransactionRequestToDtoMapper transactionRequestToDtoMapper;
    private final TransactionDtoToResponseMapper transactionDtoToResponseMapper;

    /**
     * изменение баланса в account
     * и внесение тразакции в таблицу
     */
    @Override
    public TransactionResponseDto makePayment(TransactionRequestDto transactionRequestDto) {
        log.debug("Making payment for transaction {}", transactionRequestDto);
        accountService.transferMoney(transactionRequestDto.fromAccountId(),
                transactionRequestDto.toAccountId(), transactionRequestDto.amount());
        log.debug("Payment successful for transaction {}", transactionRequestDto);

        log.debug("trying to create transaction");
        var response = transactionService.createTransaction(transactionRequestToDtoMapper
                .mapToDto(transactionRequestDto));

        log.debug("Transaction created successfully");
        return transactionDtoToResponseMapper.mapToResponse(response);
    }
}
