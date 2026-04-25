package swingy.model.validation.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

import swingy.model.validation.exception.DTOException;

public abstract class DTO {
	private static final Validator	validator;
	static {
		ValidatorFactory	factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	protected void	validate() throws DTOException {
		Set<ConstraintViolation< DTO > >	violations = validator.validate(this);
		if (!violations.isEmpty())
			throw new DTOException((Set<ConstraintViolation< ? > >) (Set< ? >) violations);
	}
	protected abstract String getData();
}