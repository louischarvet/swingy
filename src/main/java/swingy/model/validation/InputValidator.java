package swingy.model.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

// import swingy.model.validation.exception.ValidationException;

public abstract class InputValidator {
	private static final Validator	validator;
	static {
		ValidatorFactory	factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public void	validate() throws ValidationException {
		Set<ConstraintViolation< InputValidator > >	violations = validator.validate(this);
		if (!violations.isEmpty()) {
			throw new ValidationException((Set<ConstraintViolation< ? > >) (Set< ? >) violations);
		}
	}

	public abstract String	getData();

	// public abstract InputValidator	of(String data) throws ValidationException;
}