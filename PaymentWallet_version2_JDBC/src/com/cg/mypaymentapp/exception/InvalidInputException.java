package com.cg.mypaymentapp.exception;

import java.util.Arrays;

public class InvalidInputException extends RuntimeException {

	String msg;
	public InvalidInputException(String msg) {
		
		this.msg=msg;
	}

	@Override
	public String toString() {
		return msg;
	}
	
}
