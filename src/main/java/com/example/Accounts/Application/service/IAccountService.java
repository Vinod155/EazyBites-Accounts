package com.example.Accounts.Application.service;

import com.example.Accounts.Application.dto.CustomerDto;
import com.example.Accounts.Application.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {


    void createAccount(CustomerDto customerDto);

    CustomerDto fetchDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
