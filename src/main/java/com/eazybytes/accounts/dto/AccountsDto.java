package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
	
	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number should be 10 digits")
	private Long accountNumber;
	
	@NotEmpty(message = "Acount type should not be null o empty")
	private String accountType;
	
	@NotEmpty(message = "Branch Address should not be null or empty")
	private String branchAddress;

}
