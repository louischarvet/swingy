package swingy.model;

import java.lang.Exception;

@FunctionalInterface
public interface ThrowingConsumer< T, E extends Exception > {
	void	accept(T t) throws E;
}