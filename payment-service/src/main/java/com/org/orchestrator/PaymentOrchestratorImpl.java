package com.org.orchestrator;

import com.org.converters.transaction.TransactionRequestDtoToEventMapper;
import com.org.dto.TransactionCompletedEvent;
import com.org.dto.TransactionRequestDto;
import com.org.dto.TransactionResponseDto;
import com.org.facade.PaymentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentOrchestratorImpl implements PaymentOrchestrator {
    private final PaymentFacade paymentFacade;
    private final KafkaTemplate<String, TransactionCompletedEvent> kafkaTemplate;
    private final TransactionRequestDtoToEventMapper transactionRequestDtoToEventMapper;
    @Value("${kafka.topic.transaction.name}")
    private String topicTransactionName;

    /**
     * Выполнение перевода средств,
     * и отправка изменеий в user-service
     */
    @Override
    public TransactionResponseDto makePayment(TransactionRequestDto transactionRequestDto) {
        log.debug("Making payment for {}", transactionRequestDto);
        var response = paymentFacade.makePayment(transactionRequestDto);

        log.debug("Payment response {}", response);

        kafkaTemplate.send(topicTransactionName,
                transactionRequestDtoToEventMapper.map(transactionRequestDto));
        log.debug("sent payment to {}", topicTransactionName);
        return response;
    }
}
