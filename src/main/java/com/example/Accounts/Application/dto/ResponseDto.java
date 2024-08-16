package com.example.Accounts.Application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name="Response",
        description = "Schema to hold response"
)
public class ResponseDto {

    public ResponseDto(String statusCode,String statusMsg)
    {
        this.statusCode=statusCode;
        this.StatusMsg=statusMsg;
    }

    private String statusCode;

    private String StatusMsg;
}
