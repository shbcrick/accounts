package com.eazybytes.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


/**
 * @author shubham
 *
 *auditAwareImp will be the bean name
 */

@Component("auditAwareImp")
public class AuditAwareImp implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.of("ACCOUNTS_MS");
	}

}
