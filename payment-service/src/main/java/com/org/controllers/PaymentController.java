package com.org.controllers;

import com.org.dto.TransactionRequestDto;
import com.org.orchestrator.PaymentOrchestrator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentOrchestrator paymentOrchestrator;

    /**
     * Выполнить перевод денежных средств
     */
    @PostMapping("/transfer")
    ResponseEntity<?> transfer(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        var response = paymentOrchestrator.makePayment(transactionRequestDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
