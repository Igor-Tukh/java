package ru.spbau.mit.tukh.hw08.Collections;

/**
 * interface Function1 describes function with one argument.
 *
 * @param <T> is type of argument.
 * @param <R> is type of returning value.
 */
@FunctionalInterface
public interface Function1<T, R> {
    /**
     * apply method returns result of application function to given argument.
     *
     * @param t is argument.
     * @return result of application.
     */
    R apply(T t);

    /**
     * compose method returns new function with one argument which is result of composition this function and given one.
     *
     * @param g   is function to compose.
     * @param <V> is type of returning value given function.
     * @return composed function that applies this function to its input, and then applies g to the result.
     */
    default <V> Function1<T, V> compose(Function1<? super R, ? extends V> g) {
        return (T t) -> g.apply(this.apply(t));
    }
}
