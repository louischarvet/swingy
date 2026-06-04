package swingy.model.validation;

import java.lang.Exception;
import java.lang.StringBuilder;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class ValidationException extends Exception {
	private Set< ConstraintViolation< ? > >	violations = null;
	private String	message = null;

	public ValidationException(Set< ConstraintViolation< ? > > violations) {
		this.violations = violations;
		setMessage();
	}

	public ValidationException(String message) {
		this.message = message;
	}

	private void	setMessage() {
		StringBuilder sb = new StringBuilder();

		for (ConstraintViolation< ? > violation : violations) {
			sb.append(violation.getMessage()).append('\n');
		}
		this.message = sb.toString();
	}

	@Override
	public String	getMessage() {
		return message;
	}
}