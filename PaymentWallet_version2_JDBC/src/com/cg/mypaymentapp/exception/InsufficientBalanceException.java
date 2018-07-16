package com.cg.mypaymentapp.exception;

public class InsufficientBalanceException extends RuntimeException{

	String msg;
	 public InsufficientBalanceException(String msg) {
			
		this.msg=msg;
	}

	@Override
	public String toString() {
		return msg;
	}
	
}
