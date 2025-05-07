package com.org.service;

import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneDto;
import com.org.dto.PhoneResponseDto;

import java.util.List;
import java.util.UUID;

public interface PhoneService {
    void create(PhoneDto phone);

    List<PhoneDto> getAllPhonesByUserId(UUID userId);

    PhoneResponseDto addNumber(PhoneAddRequestDto phone);

    void deletePhoneByNumber(String phoneId);

    PhoneDto getPhoneByNumber(String number);

}
