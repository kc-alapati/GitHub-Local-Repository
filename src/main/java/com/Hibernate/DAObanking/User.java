package com.Hibernate.DAObanking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "accno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int accno;
	@Column(name = "name")
	String name;
	@Column(name = "email")
	String email;
	@Column(name = "password")
	String password;

	@Column(name = "Balance")
	long balance;
	@Column(name = "phone")
	String phone;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email, String password, long balance, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [accno=" + accno + ", name=" + name + ", email=" + email + ", password= CONFIDENTIAL "
				+ ", balance=" + balance + ", phone=" + phone + "]";
	}

	public int getAccno() {
		return accno;
	}

}
