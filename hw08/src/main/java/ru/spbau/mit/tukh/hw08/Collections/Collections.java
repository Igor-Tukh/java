package ru.spbau.mit.tukh.hw08.Collections;

import java.util.ArrayList;
import java.util.List;

/**
 * class Collections contains some methods which behaviour is similar to functional programming analogue.
 */
public class Collections {
    /**
     * map method applies given function to all elements of given iterable structure.
     *
     * @param function is function to apply.
     * @param a        is given iterable structure.
     * @param <T>      is argument type of function.
     * @param <R>      is returning type of function.
     * @return list consist of results of applying function to all elements.
     */
    public static <T, R> List<R> map(Function1<T, ? extends R> function, Iterable<? extends T> a) {
        List<R> ans = new ArrayList<>();
        for (T t : a) {
            ans.add(function.apply(t));
        }
        return ans;
    }

    /**
     * filter method finds all elements in iterable structure which satisfy given predicate.
     *
     * @param predicate is predicate to check.
     * @param a         is given iterable.
     * @param <T>       is predicate argument type.
     * @return list consist of all satisfying elements.
     */
    public static <T> List<T> filter(Predicate<T> predicate, Iterable<? extends T> a) {
        List<T> ans = new ArrayList<>();
        for (T t : a) {
            if (predicate.apply(t)) {
                ans.add(t);
            }
        }
        return ans;
    }

    /**
     * takeWhile finds maximum prefix of given iterable structure all elements of which satisfy given
     * predicate.
     *
     * @param predicate is predicate to check.
     * @param a         is iterable structure.
     * @param <T>       is predicate argument type.
     * @return list consist of elements of maximum satisfying prefix.
     */
    public static <T> List<T> takeWhile(Predicate<T> predicate, Iterable<? extends T> a) {
        List<T> ans = new ArrayList<>();
        for (T t : a) {
            if (predicate.apply(t)) {
                ans.add(t);
            } else {
                break;
            }
        }
        return ans;
    }

    /**
     * takeUnless finds maximum prefix of given iterable structure all elements of which doesn't satisfy given
     * predicate.
     *
     * @param predicate is predicate to check.
     * @param a         is iterable structure.
     * @param <T>       is predicate argument type.
     * @return list consist of elements of maximum no-satisfying prefix.
     */
    public static <T> List<T> takeUnless(Predicate<T> predicate, Iterable<? extends T> a) {
        return takeWhile(predicate.not(), a);
    }

    /**
     * left convolution. Calculates result of left associative applying function to elements of iterable structure.
     *
     * @param function is function.
     * @param initial  is initial value.
     * @param a        is iterable structure.
     * @param <T>      is iterable structure elements type.
     * @param <U>      is type of function returning value.
     * @return result of left convolution.
     */
    public static <T, U> U foldl(Function2<U, T, ? extends U> function, U initial, Iterable<? extends T> a) {
        for (T t : a) {
            initial = function.apply(initial, t);
        }
        return initial;
    }

    /**
     * right convolution. Calculates result of right associative applying function to elements of iterable structure.
     *
     * @param function is function.
     * @param initial  is initial value.
     * @param a        is iterable structure.
     * @param <T>      is iterable structure elements type.
     * @param <U>      is type of function returning value.
     * @return result of right convolution.
     */
    public static <T, U> U foldr(Function2<T, U, ? extends U> function, U initial, Iterable<? extends T> a) {
        List<T> list = new ArrayList<>();
        for (T t : a) {
            list.add(t);
        }
        java.util.Collections.reverse(list);
        for (T t : list) {
            initial = function.apply(t, initial);
        }
        return initial;
    }
}