package com.cg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Client {

	private WalletService walletService;
	private HashMap<String, Customer> data;

	public Client() {

		walletService = new WalletServiceImpl();

	}

	public void menu() {
		System.out.println();

		System.out.println("1. Create Account");
		System.out.println("2. Deposit Amount");
		System.out.println("3. Withdraw Amount");
		System.out.println("4. Fund Transfer ");
		System.out.println("5. Check Balance");

		Scanner sc = new Scanner(System.in);

		int choice = sc.nextInt();

		switch (choice) {

		case 1:
			System.out.println("Enter Name");
			String name = sc.next();
			System.out.println("Enter Mobile Number");
			String mobileno = sc.next();
			System.out.println("Enter Initial Balance to add");
			BigDecimal amount = sc.nextBigDecimal();

			
			try {
				walletService.createAccount(name, mobileno, amount);
			} catch (Exception e4) {
				// TODO Auto-generated catch block
				System.err.println(e4);
			}
			break;
		case 2:
			System.out.println("Enter MobileNo.");
			mobileno = sc.next();
			System.out.println("Enter amount to deposit");
			amount = sc.nextBigDecimal();

			try {
				walletService.depositAmount(mobileno, amount);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				System.err.println(e3);
			}

			break;

		case 3:
			System.out.println("Enter MobileNo.");
			mobileno = sc.next();
			System.out.println("Enter amount to withdraw");
			amount = sc.nextBigDecimal();

			try {
				walletService.withdrawAmount(mobileno, amount);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				System.err.println(e2);
			}

			break;
		case 4:
			System.out.println("Enter source MobileNo.");
			String sourceMobileNo = sc.next();
			System.out.println("Enter destination MobileNo.");
			String targetMobileNo = sc.next();
			System.out.println("Enter amount to transfer");
			amount = sc.nextBigDecimal();

			try {
				walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.err.println(e1);
			}

			break;
		case 5:
			System.out.println("Enter mobile number to check balance");
			mobileno = sc.next();

			try {
				Customer customer = walletService.showBalance(mobileno);

				System.out.println("Your balance is: " + customer.getWallet().getBalance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
			break;
		default:
			System.exit(0);
			break;

		}

	}

	public static void main(String[] args) {

		Client client = new Client();

		while (true) {
			client.menu();
		}

	}
}
