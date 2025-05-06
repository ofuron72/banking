package com.org.service;

import com.org.dto.EmailDto;

import java.util.List;
import java.util.UUID;

public interface EmailService {
    void create(EmailDto emailDto);

    List<EmailDto> getAllEmailsByUserId(UUID userId);

}
