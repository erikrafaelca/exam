package com.uxp.exam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="USER")
public class User {
	
	public enum UserStatus {
		ACTIVATED,
		DEACTIVATED
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String password;
	
	private UserStatus status;

	public User(){}
	
	public User(String name, String pwd) {
		this.name = name;
		this.password = pwd;
		this.status = UserStatus.ACTIVATED;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", status=" + status + "]";
	}

}