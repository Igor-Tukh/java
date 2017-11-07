package ru.spbau.mit.tukh.hw08.Function.Predicate;

import ru.spbau.mit.tukh.hw08.Function.Function1.Function1;

/**
 * interface Predicate describes predicate of one argument(expression).
 *
 * @param <T> is type of argument.
 */
public interface Predicate<T> extends Function1<T, Boolean> {
    /**
     * generates new predicate result of which calculating is logical or of this and given.
     *
     * @param predicate is second predicate.
     * @return new predicate which is logical or of this and given.
     */
    default Predicate<T> or(Predicate<? super T> predicate) {
        return (t -> (this.apply(t) || predicate.apply(t)));
    }

    /**
     * generates new predicate result of which calculating is logical and of this and given.
     *
     * @param predicate is second predicate.
     * @return new predicate which is logical and of this and given.
     */
    default Predicate<T> and(Predicate<? super T> predicate) {
        return (t -> (this.apply(t) && predicate.apply(t)));
    }

    /**
     * returs negation of this predicate.
     *
     * @return predicate which is negation of this.
     */
    default Predicate<T> not() {
        return (t -> !(this.apply(t)));
    }

    /**
     * always true predicate.
     *
     * @return predicate result of which applicating is always true.
     */
    static Predicate ALWAYS_TRUE() {
        return (t -> Boolean.TRUE);
    }

    /**
     * always false predicate.
     *
     * @return predicate result of which applicating is always false.
     */
    static Predicate ALWAYS_FALSE() {
        return (t -> Boolean.FALSE);
    }
}
