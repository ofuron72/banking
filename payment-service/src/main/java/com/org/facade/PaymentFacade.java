package com.org.facade;

import com.org.dto.TransactionRequestDto;
import com.org.dto.TransactionResponseDto;

public interface PaymentFacade {
    TransactionResponseDto makePayment(TransactionRequestDto transactionRequestDto);
}
