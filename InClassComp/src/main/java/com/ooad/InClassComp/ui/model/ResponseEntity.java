package com.ooad.InClassComp.ui.model;

public class ResponseEntity {

	private String status;
	
	private String message;

	public String getStatus() {
		return status;
	}

	public ResponseEntity(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ResponseEntity() {
		super();
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
