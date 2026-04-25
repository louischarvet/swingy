package swingy.model.validation.exception;

import java.lang.Exception;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class DTOException extends Exception {
//	private String	messages = null;
	private Set< ConstraintViolation< ? > >	violations = null;

	public DTOException(Set< ConstraintViolation< ? > > violations) {
		this.violations = violations;
	}

	@Override
	public String	getMessage() {
		String	message = "";
		for (ConstraintViolation< ? > violation : violations) {
			message += violation.getMessage() + "\n";
		}
		return message;
	}
}