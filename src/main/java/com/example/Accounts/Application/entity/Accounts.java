package com.example.Accounts.Application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Accounts extends BaseEntity{

    private Integer customerId;

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
