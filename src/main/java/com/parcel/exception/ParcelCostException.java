package com.parcel.exception;

import java.io.Serial;

public class ParcelCostException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public ParcelCostException(String message){
		super(message);
	}

}
