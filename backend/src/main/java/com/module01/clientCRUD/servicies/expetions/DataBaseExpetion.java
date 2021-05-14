package com.module01.clientCRUD.servicies.expetions;

public class DataBaseExpetion extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DataBaseExpetion(String msg) {
		super(msg);
	}
}
