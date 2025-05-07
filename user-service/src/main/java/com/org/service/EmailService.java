package com.org.service;

import com.org.dto.EmailAddRequestDto;
import com.org.dto.EmailDto;
import com.org.dto.EmailResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmailService {
    void create(EmailDto emailDto);

    List<EmailDto> getAllEmailsByUserId(UUID userId);

    EmailResponseDto addEmail(EmailAddRequestDto email);

    void deleteEmailByAddress(String address);

    EmailDto getEmailByAddress(String address);

}
