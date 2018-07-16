package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaytmapp.util.DBUtil;

public class WalletRepoImpl implements WalletRepo {

	private DBUtil dbUtil;

	public WalletRepoImpl() {
		super();


	}

	@Override
	public Customer findOne(String mobileNo) {

		Customer customer = null;
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement pstm = con.prepareStatement("select * from Customer where phone_number = ?");
			pstm.setString(1, mobileNo);
			ResultSet res = pstm.executeQuery();
			if (res.next() == false) {
				return null;
			}
			Wallet wallet = new Wallet(res.getBigDecimal(3));
			customer = new Customer(res.getString(2), res.getString(1), wallet);

		} catch (Exception e) {
			return null;
		}

		return customer;
	}

	@Override
	public boolean save(Customer customer) {
		// TODO Auto-generated method stub
		try (Connection con = DBUtil.getConnection()) {
			// generate empId
			Statement stm = con.createStatement();
			PreparedStatement pstm = con.prepareStatement("insert into Customer values(?,?,?)");

			
			pstm.setString(1, customer.getMobileNo());
			pstm.setString(2, customer.getName());
			pstm.setBigDecimal(3, customer.getWallet().getBalance());

			pstm.execute();
		} catch (Exception e) {
			
			return false;
		}

		return true;
	}

	@Override
	public boolean update(Customer customer) {
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement pstm = con.prepareStatement("update Customer set balance=? where phone_number = ?");
			pstm.setBigDecimal(1, customer.getWallet().getBalance());
			pstm.setString(2, customer.getMobileNo());
			ResultSet res = pstm.executeQuery();
			if (res.next() == false) {
				return false;
			}

		} catch (Exception e) {
			throw new InvalidInputException("Mobile number does not exist");
		}

		return false;
	}

}
