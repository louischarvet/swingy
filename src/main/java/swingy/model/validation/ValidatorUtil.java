package swingy.model.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import jakarta.validation.ConstraintViolation;

public class ValidatorUtil {
    private static final Validator validator = Validation
		.buildDefaultValidatorFactory()
		.getValidator();

	private ValidatorUtil() {}

    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        return validator.validate(object);
    }
}
