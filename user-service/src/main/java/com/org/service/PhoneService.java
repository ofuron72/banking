package com.org.service;

import com.org.dto.PhoneDto;

import java.util.List;
import java.util.UUID;

public interface PhoneService {
    void create(PhoneDto phone);

    List<PhoneDto> getAllPhonesByUserId(UUID userId);

}
