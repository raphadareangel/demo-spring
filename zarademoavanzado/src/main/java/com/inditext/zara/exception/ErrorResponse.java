package com.inditext.zara.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inditext.zara.model.Pricing;

import lombok.Getter;
import lombok.Setter;

/**
 * Comment getter and setter tags if you do not have lombock
 */
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    /**
     * Uncomment getters and setters if you do not have lombock in your IDE
     */
    	    // Getters and setters
    
//    public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

	
}
