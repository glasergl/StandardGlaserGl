package de.glasergl.standard.util;

/**
 * Immutable representation of a tuple of an object of type T1 and an object of
 * type T2
 *
 * @author Gabriel Glaser
 */
public final class Tuple<T1, T2> {
    private final T1 t1;
    private final T2 t2;

    public Tuple(final T1 t1, final T2 t2) {
	super();
	this.t1 = t1;
	this.t2 = t2;
    }

    public T1 getFirst() {
	return t1;
    }

    public T2 getSecond() {
	return t2;
    }
}
