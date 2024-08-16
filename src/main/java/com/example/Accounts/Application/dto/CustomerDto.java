package com.example.Accounts.Application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Schema(
        name="Customer",
        description = "Schema to hold Customer details"
)
public class CustomerDto {
    @Schema(
            description = "Name of the Customer",name="Vinnie Thakur"
    )
    @NotEmpty(message = "Name Cannot be null or empty")
    @Size(min=5,max=30,message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the Customer",name="Vinn@gmail.com"
    )
    @NotEmpty(message = "email Cannot be null or empty")
    @Email(message = "Email address should be a valid address")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer",name="1234567897"
    )
    @Pattern(regexp = "$|[0-9]{10}" , message = "Mobile must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
