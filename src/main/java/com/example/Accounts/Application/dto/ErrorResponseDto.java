package com.example.Accounts.Application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@Schema(
        name="Error Response",
        description = "Schema to hold Error response information"
)
public class ErrorResponseDto {

    public ErrorResponseDto(String apiPath,HttpStatus errorCode,String errorMessage,LocalDateTime errorTime)
    {
        this.apiPath=apiPath;
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
        this.errorTime=errorTime;
    }

    private String apiPath;

    private HttpStatus errorCode;

    private String errorMessage;

    private LocalDateTime errorTime;
}
