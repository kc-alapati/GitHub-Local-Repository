package com.Hibernate.DAObanking;

public interface BankingDAO {
	
	void register();

	void login();

	void changePassword();

	void transferAmount();

	void checkBalance();

	void updateProfile();

	void checkProfile();

	void delete();

}
