package com.org.orchestrator;

import com.org.dto.TransactionRequestDto;
import com.org.dto.TransactionResponseDto;
import org.springframework.stereotype.Service;


@Service
public interface PaymentOrchestrator {
    TransactionResponseDto makePayment(TransactionRequestDto transactionRequestDto);

}
