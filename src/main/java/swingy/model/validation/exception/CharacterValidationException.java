package swingy.model.validation.exception;

import java.lang.Exception;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

import swingy.model.character.Character;

public class CharacterValidationException extends ValidationException {
	private	String	messages;

	public CharacterValidationException(Set< ConstraintViolation< Character > > violations) {
		super((Set< ConstraintViolation< ? > >) (Set< ? >) violations);

		this.messages = this.getClass().getSimpleName() + "\n";
		for (ConstraintViolation< ? > violation : violations) {
			this.messages += ((ConstraintViolation< Character >)violation).getInvalidValue()
				+ " : " + violation.getMessage() + "\n";
		}
	}

	public String	getMessages() {
		return this.messages;
	}
}