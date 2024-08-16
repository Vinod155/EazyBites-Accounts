package com.example.Accounts.Application.repository;

import com.example.Accounts.Application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {

    Customer findByMobileNumber(String mobileNumber);

}
