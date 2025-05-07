package com.org.service;

import com.org.dto.PhoneAddRequestDto;
import com.org.dto.PhoneAddResponseDto;
import com.org.dto.PhoneDto;

import java.util.List;
import java.util.UUID;

public interface PhoneService {
    void create(PhoneDto phone);

    List<PhoneDto> getAllPhonesByUserId(UUID userId);

    PhoneAddResponseDto addNumber(PhoneAddRequestDto phone);

    void deletePhoneById(UUID phoneId);

}
