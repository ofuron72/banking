package com.org.kafka;

import com.org.converters.account.AccountCreateEventToDtoMapper;
import com.org.dto.AccountCreateEvent;
import com.org.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@KafkaListener(topics = "${kafka.topic.account.name}", groupId = "account-group")
public class KafkaListenerService {
    private final AccountService accountService;
    private final AccountCreateEventToDtoMapper accountCreateEventToDtoMapper;

    @KafkaHandler
    public void handleAccountCreateEvent(AccountCreateEvent accountCreateEvent) {
        accountService.create(accountCreateEventToDtoMapper
                .mapSendDtoTOAccountDto(accountCreateEvent));
    }
}
