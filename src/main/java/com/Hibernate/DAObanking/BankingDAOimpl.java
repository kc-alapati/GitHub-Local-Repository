package com.Hibernate.DAObanking;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class BankingDAOimpl implements BankingDAO {

	SessionFactory sf = null;
	Scanner scan = new Scanner(System.in);

	public SessionFactory getSessionFactory() {
		if (sf == null) {
			// create the hibernate configuration
			Configuration configuration = new Configuration();

			// configure hibernate.cfg.xml
			configuration.configure("hibernate.cfg.xml");

			// create service registry
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			// build session factory
			sf = configuration.buildSessionFactory(serviceRegistry);

			return sf;
		}
		return sf;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void register() {
		Session session = null;
		

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your name");
			String name = scan.nextLine();
			System.out.println("Enter Your email");
			String email = scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			System.out.println("Enter Your phone number");
			String phone = scan.nextLine();
			System.out.println("Enter the Amount you want to deposit");
			long bal = scan.nextLong();
			
			User u = new User();
			u.setName(name);
			u.setEmail(email);
			u.setPassword(pwd);
			u.setPhone(phone);
			u.setBalance(bal);
			
			session.persist(u);
			session.getTransaction().commit();
			System.out.println("Registration Successful");
			System.out.println("Your account number is: "+u.getAccno());
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Registration Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void login() {

		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				System.out.println("Your Balance is " + user.getBalance());
			} else {
				System.out.println("incorrect details ... Please try again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void changePassword() {
		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				System.out.println("Enter your new Password ");
				String newPwd = scan.next();
				user.setPassword(newPwd);
				session.update(user);

				session.getTransaction().commit();
				System.out.println("Password Updation Successful");
			} else {
				System.out.println("Incorrect details ... Please try to Login again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void transferAmount() {

		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				System.out.println("Enter the account number to transfer amount ");
				int newAcc = scan.nextInt();
				scan.nextLine();

				Criteria criteria1 = session.createCriteria(User.class);
				criteria1.add(Restrictions.eq("accno", newAcc));
				User user2 = (User) criteria1.uniqueResult();

				if (user2 != null) {
					System.out.println("Enter the amount to be Transferred");
					long amt = scan.nextLong();
					long bal = user.getBalance();
					long bal2 = user2.getBalance();

					if (amt <= bal) {
						bal = bal - amt;
						user.setBalance(bal);
						bal2 = bal2 + amt;
						user2.setBalance(bal2);
						session.update(user);
						session.update(user2);

					} else {
						System.out.println("Your account balance is less than enterered amount to transfer");
						System.out.println("Please try with lesser amount");
					}
				} else {
					System.out.println("Enter Correct account number to Transfer Amount");
				}

				session.getTransaction().commit();
				System.out.println("Transfer Successful");
			} else {
				System.out.println("Incorrect details ... Please try to Login again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some Error occured,Please Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void checkBalance() {

		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				System.out.println("Your Balance is " + user.getBalance());
			} else {
				System.out.println("incorrect details ... Please try again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void updateProfile() {
		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				System.out.println("Which information would you like to update?");
		        System.out.println("1. Email");
		        System.out.println("2. Password");
		        System.out.println("3. Phone");
		        System.out.print("Enter your choice (1-3): ");
		        
		        int choice = scan.nextInt();
		        scan.nextLine();
		        
		        switch(choice) {
		        case 1:
	                System.out.print("Enter new email: ");
	                String newEmail =scan.nextLine(); 
	                user.setEmail(newEmail);
	                session.update(user);
	                session.getTransaction().commit();
	                System.out.println("Email Updated Successfully");
	                
	                break;
	                
	            case 2:
	                System.out.print("Enter new password: ");
	                String newPwd =scan.nextLine(); 
	                user.setPassword(newPwd);
	                session.update(user);
	                session.getTransaction().commit();
	                System.out.println("Password Updated Successfully");
	                break;
	                
	            case 3:
	            	System.out.print("Enter new Phone number: ");
	                String newPhone =scan.nextLine(); 
	                user.setPhone(newPhone);
	                session.update(user);
	                session.getTransaction().commit();
	                System.out.println("Phone number Updated Successfully");
	                break;
	                
	            default:
	                System.out.println("Invalid choice!");
	                break;

		        }
			} else {
				System.out.println("incorrect details ... Please try again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void delete() {
		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				session.delete(user);
				session.getTransaction().commit();
				System.out.println("Your Account has been Deleted");
			} else {
				System.out.println("incorrect details ... Please try again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public void checkProfile() {

		Session session = null;

		try {
			sf = getSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			
			System.out.println("Enter Your account number");
			int accno = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter Your password");
			String pwd = scan.nextLine();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("accno", accno));
			criteria.add(Restrictions.eq("password", pwd));

			User user = (User) criteria.uniqueResult();

			if (user != null) {
				System.out.println("Login successful");
				System.out.println(user);
			} else {
				System.out.println("incorrect details ... Please try again");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login Failed, Try Again");
		}

		finally {
			session.close();
		}

	}

}
