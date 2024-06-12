package com.eazybytes.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** 
 * A class designated with the MappedSuperclass annotation can be mapped in the same way as an entity
 * except that the mappings will apply only to its subclasses since no table exists for the mapped
 * superclass itself
 * 
 * 
 * **/
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@CreatedBy
	@Column( updatable = false)
	private String createdBy;
	
	@LastModifiedDate
	@Column(insertable = false )
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	@Column(insertable =  false )
	private String updatedBy;

}
