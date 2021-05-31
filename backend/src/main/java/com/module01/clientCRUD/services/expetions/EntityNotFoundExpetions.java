package com.module01.clientCRUD.services.expetions;

public class EntityNotFoundExpetions extends RuntimeException{
		
	private static final long serialVersionUID = 1L;
		
	public EntityNotFoundExpetions(String msg) {
		super(msg);
	}
}
