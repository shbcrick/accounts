package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

/**
 * @author shubham
 *
 */
public interface IAccountsService {
	

	/** 
	 * @param customerDto- CustomerDto object 
	 * */
	void createAccount(CustomerDto customerDto);
	
	/**
	 * 
	 * **/
	CustomerDto fetchAccounts(String mobileNumber);
	
	boolean updateAccount(CustomerDto customerDto);
	
	
	boolean deleteAccount(String mobileNumber);

}
