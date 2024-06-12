package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name = "customer",
		description = "put msg"
		)

public class CustomerDto {

	@Schema(
			description = "Customer Name",
			example = "Shubham Pandey"
			)
	
	@NotEmpty(message = "Name should not be empty")
	@Size( min = 5, max = 30)
	private String name;

	@Schema(
			description = "Customer email",
			example = "shbcrick@gmail.com"
			)
	@NotEmpty(message = " email fiedl should not be empyt")
	@Email(message = "Email should be valid value")
	private String email;

	@Schema(
			description = "Customer Mobile Number",
			example = "800xxx4205"
			)
	@Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number should be 10 digits")
	private String mobileNumber;

	private AccountsDto accountsDto;

}
