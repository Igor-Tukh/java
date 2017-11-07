package ru.spbau.mit.tukh.hw08.Function.Function2;

import ru.spbau.mit.tukh.hw08.Function.Function1.Function1;

/**
 * interface Function2 describes function with two arguments.
 *
 * @param <T> is type of first argument.
 * @param <U> is type of second argument.
 * @param <R> is type of returning value.
 */
public interface Function2<T, U, R> {
    /**
     * apply method returns result of application function to given arguments.
     *
     * @param t is the first argument.
     * @param u is the second argument
     * @return result of application.
     */
    R apply(T t, U u);

    /**
     * compose method returns function with is composition of this function and given one.
     *
     * @param g   is function with one argument to compose to this.
     * @param <V> is type of returning value given function.
     * @return return function of two arguments which is result of composition given function and this one.
     */
    default <V> Function2<T, U, V> compose(Function1<? super R, ? extends V> g) {
        return (T t, U u) -> g.apply(this.apply(t, u));
    }

    /**
     * bind1 method substitutes given argument as a second argument of this function.
     *
     * @param u is argument to substitute.
     * @return one argument function which is result of substitution argument to this function.
     */
    default Function1<T, R> bind2(U u) {
        return (T t) -> this.apply(t, u);
    }

    /**
     * bind1 method substitutes given argument as a first argument of this function.
     *
     * @param t is argument to substitute.
     * @return one argument function which is result of substitution argument to this function.
     */
    default Function1<U, R> bind1(T t) {
        return (U u) -> this.apply(t, u);
    }

    /**
     * curry method substituting second argument with a given argument.
     *
     * @param u is argument to substitute.
     * @return one argument function -- result of substituting.
     */
    default Function1<T, R> curry(U u) {
        return this.bind2(u);
    }
}
