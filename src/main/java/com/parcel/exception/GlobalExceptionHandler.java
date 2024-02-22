package com.parcel.exception;

import com.parcel.enums.ResponseStatus;
import com.parcel.response.ParcelApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ParcelCostException.class)
	public ResponseEntity<?> handleParcelCostException(ParcelCostException e){
		return new ResponseEntity<>(ParcelApiResponse.builder()
				.status(ResponseStatus.ERROR)
				.isError(Boolean.TRUE)
				.response(e.getMessage())
				.build(),HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(OverWeightException.class)
	public ResponseEntity<?> handleOverWeightException(OverWeightException e){
		return new ResponseEntity<>(ParcelApiResponse.builder()
				.status(ResponseStatus.REJECT)
				.isError(Boolean.TRUE)
				.response(e.getMessage())
				.build(),HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception e){

		return new ResponseEntity<>(ParcelApiResponse.builder()
				.status(ResponseStatus.ERROR)
				.isError(Boolean.TRUE)
				.response("Oops! Something went wrong: "+e.getMessage())
				.build(),HttpStatus.BAD_REQUEST);
	}
	
}
