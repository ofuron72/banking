package com.org.objects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserProjection {
    UUID getId();
    String getFullName();
    LocalDate getBirthDate();
    List<String> getNumber();
    List<String> getEmail();
    BigDecimal getInitialDeposit();
    BigDecimal getBalance();
}
