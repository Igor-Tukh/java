package ru.spbau.mit.tukh.hw08.Collections;

/**
 * interface Predicate describes predicate of one argument(expression).
 *
 * @param <T> is type of argument.
 */
@FunctionalInterface
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
    static <T> Predicate<T> alwaysTrue() {
        return (t -> Boolean.TRUE);
    }

    /**
     * always false predicate.
     *
     * @return predicate result of which applicating is always false.
     */
    static <T> Predicate<T> alwaysFalse() {
        return (t -> Boolean.FALSE);
    }
}
