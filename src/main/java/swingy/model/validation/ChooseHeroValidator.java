package swingy.model.validation;

import jakarta.validation.constraints.Pattern;

public class ChooseHeroValidator extends InputValidator {
	@Pattern(
		regexp = "^[0-9]+$",
		message = "Choose a hero by typing their index (from 1)." // cancel ?
	)
	private String	data;
	private	int	index;

	private ChooseHeroValidator(String data, int index) {
		this.data = data;
		this.index = Integer.parseInt(data);
	}

	public String	getData() {
		return data;
	}

	public int	getIndex() {
		return index;
	}

	public static ChooseHeroValidator	of(String data, int maxSize) throws Exception {
		if (data.equalsIgnoreCase("CANCEL"))
			throw new CancelException();

		int	index = Integer.parseInt(data);
		if (index > maxSize)
			throw new ValidationException("Index is out of bounds.");

		ChooseHeroValidator	validator = new ChooseHeroValidator(data, index);
		validator.validate();

		return validator;
	}
}