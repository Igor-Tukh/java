package ru.spbau.mit.tukh.Calculator;

/**
 * exception for symbol which can't be parse.
 */
class IncorrectCharacterException extends Exception {
    IncorrectCharacterException(String message) {
        super(message);
    }
}
