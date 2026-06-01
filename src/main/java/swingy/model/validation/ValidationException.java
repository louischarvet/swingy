package swingy.model.validation;

import java.lang.Exception;
import java.lang.StringBuilder;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class ValidationException extends Exception {
	private Set< ConstraintViolation< ? > >	violations = null;

	public ValidationException(Set< ConstraintViolation< ? > > violations) {
		this.violations = violations;
	}

	@Override
	public String	getMessage() {
		StringBuilder sb = new StringBuilder();

		for (ConstraintViolation< ? > violation : violations) {
			sb.append(violation.getMessage()).append('\n');
		}
		return sb.toString();
	}
}