package com.revature.dto;
import com.revature.exception.*;
import com.revature.controller.*;
import java.util.Objects;

public class ExceptionMessageDTO {

	private String message;
	
	// Constructors
	public ExceptionMessageDTO() {
	}
	
	public ExceptionMessageDTO(Exception e) {
		this.message = e.getMessage();
	}
	//added by gamal
	public ExceptionMessageDTO(String m) {
		this.message = m;
	}
	
	// Methods

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExceptionMessageDTO [message=" + message + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionMessageDTO other = (ExceptionMessageDTO) obj;
		return Objects.equals(message, other.message);
	}
	
}

