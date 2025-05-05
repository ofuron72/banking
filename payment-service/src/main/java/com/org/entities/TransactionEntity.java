package com.org.entities;

import com.org.objects.TransactionsStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Column(name = "id")
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "from_account")
    private AccountEntity fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account")
    private AccountEntity toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionsStatus status;
}
