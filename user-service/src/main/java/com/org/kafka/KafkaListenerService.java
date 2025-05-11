package com.org.kafka;

import com.org.dto.TransactionCompletedEvent;
import com.org.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@KafkaListener(topics = "${kafka.topic.transaction.name}", groupId = "payment-group")
public class KafkaListenerService {
    private final AccountService accountService;

    @KafkaHandler
    public void handleAccountCreateEvent(TransactionCompletedEvent transactionCompletedEvent) {
        accountService.transferMoney(transactionCompletedEvent.fromAccountId(),
                transactionCompletedEvent.toAccountId(),
                transactionCompletedEvent.amount());

    }
}
