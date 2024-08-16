package com.example.Accounts.Application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Schema(
        name="Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @Schema(description = "Customer Account Number")
    @NotEmpty(message = "account number Cannot be null or empty")
    @Pattern(regexp = "$|[0-9]{10}" , message = "Mobile must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Customer Account Type")
    @NotEmpty(message = "account type Cannot be null or empty")
    private String accountType;

    @Schema(description = "Customer Bank Address")
    @NotEmpty(message = "branch address Cannot be null or empty")
    private String branchAddress;
}
