package com.example.Accounts.Application.service;


import com.example.Accounts.Application.constants.AccountsConstants;
import com.example.Accounts.Application.dto.AccountsDto;
import com.example.Accounts.Application.dto.CustomerDto;
import com.example.Accounts.Application.dto.ErrorResponseDto;
import com.example.Accounts.Application.entity.Accounts;
import com.example.Accounts.Application.entity.Customer;
import com.example.Accounts.Application.exception.CustomerAlreadyExistsException;
import com.example.Accounts.Application.exception.ResourceNotFoundException;
import com.example.Accounts.Application.mapper.AccountsMapper;
import com.example.Accounts.Application.mapper.CustomerMapper;
import com.example.Accounts.Application.repository.AccountRepository;
import com.example.Accounts.Application.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService{

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        Customer optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer!=null)
        {
            throw new CustomerAlreadyExistsException("Customer Already Exist"+customerDto.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        Accounts account=createNewAccount(customer);
        accountRepository.save(account);
    }

    @Override
    public CustomerDto fetchDetails(String mobileNumber) {
        Customer customerDetails=customerRepository.findByMobileNumber(mobileNumber);
        if(customerDetails==null)
        {
            throw new ResourceNotFoundException("Customer","mobileNumber",mobileNumber);
        }
        Accounts accountDetails=accountRepository.findByCustomerId(customerDetails.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","customerId",customerDetails.getCustomerId().toString())
        );
       CustomerDto response= CustomerMapper.mapToCustomerDto(customerDetails,new CustomerDto());
       response.setAccountsDto(AccountsMapper.mapToAccountsDto(accountDetails,new AccountsDto()));

      return response;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        Accounts accountDetails=accountRepository.findById(customerDto.getAccountsDto().getAccountNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Accounts","account Number",customerDto.getAccountsDto().getAccountNumber().toString())
        );
        Accounts updatedAccount=AccountsMapper.mapToAccounts(customerDto.getAccountsDto(),accountDetails);
        accountRepository.save(updatedAccount);
        Customer customer=customerRepository.findById(updatedAccount.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Customer","CustomerId",updatedAccount.getCustomerId().toString())
        );
        Customer updatedCustomer=CustomerMapper.mapToCustomer(customerDto,customer);
        customerRepository.save(updatedCustomer);
     return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber);
        if(customer==null) {
            throw new ResourceNotFoundException("Customer", "customerId", mobileNumber);
        }
        accountRepository.findDeleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
        return true;
    }

    private Accounts createNewAccount(Customer customer)
   {
       Accounts newAccount=new Accounts();
       newAccount.setCustomerId(customer.getCustomerId());
       long randomAccountNumber=1000000000L+new Random().nextInt(90000000);
       newAccount.setAccountNumber(randomAccountNumber);
       newAccount.setAccountType(AccountsConstants.SAVINGS);
       newAccount.setBranchAddress(AccountsConstants.ADDRESS);
       return newAccount;
   }
}
