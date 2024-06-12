package com.eazybytes.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"This mobile number is already present in our DB" + customer.getMobileNumber());

		}
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccounts(savedCustomer));

	}

	private Accounts createNewAccounts(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);

		return newAccount;

	}

	@Override
	public CustomerDto fetchAccounts(String mobileNumber) {
		Customer customerRecords = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

		Accounts accounts = accountsRepository.findByCustomerId(customerRecords.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Customer_Id",
						customerRecords.getCustomerId().toString()));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customerRecords, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
							accountsDto.getAccountNumber().toString()));
			AccountsMapper.mapToAccounts(accountsDto, accounts);
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));
			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		// TODO Auto-generated method stub
		boolean isDeleted =  false;
		Customer customer =  customerRepository.findByMobileNumber(mobileNumber)
		.orElseThrow(() -> new ResourceNotFoundException("Customer ", "MobileNumber ", mobileNumber));
		
		if(customer != null) {
			
		Accounts account = 	accountsRepository.findByCustomerId(customer.getCustomerId())
			.orElseThrow(() -> new ResourceNotFoundException("Account ", "CustomerId ", customer.getCustomerId().toString()));
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		isDeleted = true ; 
		}
		
		return isDeleted;
	}
}
