package com.Hibernate.DAObanking;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		BankingDAOimpl ref = new BankingDAOimpl();
		ref.getSessionFactory();

		System.out.println("Welcome to DAO Bank");

		while (true) {

			System.out.println("Choose From Below Menu");
			System.out.println("1=======>REGISTRATION");
			System.out.println("2=======>LOGIN");
			System.out.println("3=======>CHECK BALANCE");
			System.out.println("4=======>TRANSFER AMOUNT");
			System.out.println("5=======>UPDATE PASSWORD");
			System.out.println("6=======>UPDATE PROFILE");
			System.out.println("7=======>DELETE ACCOUNT");
			System.out.println("8=======>CHECK PROFILE");
			System.out.println("9=======>STOP ");

			System.out.println("To proceed ahead please enter your choice");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.register();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 2:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.login();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 3:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.checkBalance();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 4:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.transferAmount();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 5:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.changePassword();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 6:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.updateProfile();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 7:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.delete();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			case 8:
				System.out.println("++++++++++++++++++++++++++++++");
				ref.checkProfile();
				System.out.println("++++++++++++++++++++++++++++++");
				break;
			default:
				System.out.println("++++++++++++++++++++++++++++++");
				System.out.println("Thank You For Using DAO Bank Online Services...Tata Bye Bye....");
				System.exit(0);
			}
			
		}
	}
}
