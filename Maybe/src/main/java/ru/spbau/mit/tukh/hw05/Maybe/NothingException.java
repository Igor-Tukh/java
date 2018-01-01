package ru.spbau.mit.tukh.hw05.Maybe;

/**
 * NothingExceptions throws when value is requesting by Maybe method get, but exemplar is nothing actually.
 */
public class NothingException extends Throwable {
    NothingException(String message) {
        super(message);
    }
}
