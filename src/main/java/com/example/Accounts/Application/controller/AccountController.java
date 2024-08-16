package com.example.Accounts.Application.controller;


import com.example.Accounts.Application.constants.AccountsConstants;
import com.example.Accounts.Application.dto.CustomerDto;
import com.example.Accounts.Application.dto.ErrorResponseDto;
import com.example.Accounts.Application.dto.ResponseDto;
import com.example.Accounts.Application.entity.Customer;
import com.example.Accounts.Application.mapper.CustomerMapper;
import com.example.Accounts.Application.repository.AccountRepository;
import com.example.Accounts.Application.repository.CustomerRepository;
import com.example.Accounts.Application.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

@Tag(
        name = "CRUD REST API for Accounts in Vinnie Bank",
        description = "CRUD REST API in Vinnie Bank to CREATE , UPDATE ,FETCH and DELETE account details"
)
@RestController
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final IAccountService accountService;
    private final CustomerRepository customerRepository;

    public AccountController(IAccountService accountService, CustomerRepository customerRepository)
    {
        this.accountService=accountService;
        this.customerRepository = customerRepository;
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create Account and Customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto)
    {
        accountService.createAccount(customerDto);
        ResponseDto responseDto=new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to Fetch Account and Customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetchdetails")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = "$|[0-9]{10}" , message = "Mobile must be 10 digits")
                                                                   @RequestParam String mobileNumber)
    {
       CustomerDto response=accountService.fetchDetails(mobileNumber);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to Update Account and Customer"
    )
    @ApiResponses({
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status UPDATED"
    ), @ApiResponse(
            responseCode = "500",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )}
    )
    @PutMapping("/update")
    public ResponseEntity<?> updateAccounts(@Valid @RequestBody CustomerDto customerDto)
    {
        boolean response=accountService.updateAccount(customerDto);
        return response?new ResponseEntity<>("Account updated Successfully",HttpStatus.OK):
                new ResponseEntity<>("Account not updated ",HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to Delete Account and Customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status DELETED"
    )
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<?> deleteAccounts(@Pattern(regexp = "$|[0-9]{10}" , message = "Mobile must be 10 digits")
                                                    @PathVariable String mobileNumber)
    {
        boolean isDeleted=accountService.deleteAccount(mobileNumber);
        if(isDeleted)
        {
            return new ResponseEntity<>("Account deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Account not  deleted",HttpStatus.BAD_REQUEST);

        }
    }

}
