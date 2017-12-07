package ru.spbau.mit.tukh.hw05.Maybe;


import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * class Maybe contains value (except null) or nothing.
 *
 * @param <T> is type of storing value.
 */
public class Maybe<T> {
    static private Maybe nothing = new Maybe<>(null);
    private T value;

    private Maybe(T t) {
        value = t;
    }

    /**
     * just returns Maybe which contains value.
     *
     * @param t   is value which returning Maybe will storage.
     * @param <T> is type of value which Maybe storage.
     * @return new Maybe containing value.
     */
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    /**
     * nothing return empty Maybe.
     *
     * @param <T> is type of shell.
     * @return shell which contains nothing.
     */
    public static <T> Maybe<T> nothing() {
        return nothing;
    }

    /**
     * get the value of not-empty shell.
     *
     * @return value if Maybe isn't empty and throws exception otherwise.
     * @throws NothingException if shel is empty.
     */
    public T get() throws NothingException {
        if (value != null) {
            return value;
        }
        throw new NothingException("get: this is nothing");
    }

    /**
     * isPresent checks if shell is empty.
     *
     * @return true if Maybe contains value and false otherwise.
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * map executes given function to not-empty shell and returns new nothing otherwise.
     *
     * @param mapper is function which could be executed to not-empty shell with returning type U.
     * @param <U>    is function returning type.
     * @return empty shell for empty shell and result of map in shel otherwise.
     */
    public <U> Maybe<U> map(Function<? super T, U> mapper) {
        if (!isPresent()) {
            return Maybe.nothing();
        }

        return Maybe.just(mapper.apply(value));
    }
}
