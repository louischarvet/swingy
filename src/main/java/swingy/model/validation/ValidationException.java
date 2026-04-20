package swingy.model.validation;

import java.lang.Exception;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public abstract class ValidationException extends Exception {
	protected Set< ConstraintViolation< ? > >	violations = null;

	protected ValidationException(Set< ConstraintViolation< ? > > violations) {
		this.violations = violations;
	}
	public abstract String	getMessages();
}