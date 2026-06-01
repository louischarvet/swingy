package swingy.model;

import java.lang.Exception;

@FunctionalInterface
public interface ThrowingBiConsumer< T, U, E extends Exception > {
	void	accept(T t, U u) throws E;
}