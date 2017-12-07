package ru.spbau.mit.tukh.hw05.Maybe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Main class. Reads numbers in files and writes their squares.
 */
public class Main {
    /**
     * main method. Tries to read all numbers in file input.txt and for each successful read writes line with its square
     * to output.txt. For other writes null.
     * @param args may contain everything, it doesn't matter.
     * @throws IOException according to files input.txt and output.txt.
     */
    public static void main(String[] args) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            PrintWriter out = new PrintWriter("output.txt")) {
            StringTokenizer in;

            String string;
            Integer number;
            Maybe<Integer> maybe;

            while ((string = bufferedReader.readLine()) != null) {
                in = new StringTokenizer(string);

                while (in.hasMoreTokens()) {
                    try {
                        number = Integer.parseInt(in.nextToken());
                        maybe = Maybe.just(number);
                    } catch (NumberFormatException e) {
                        maybe = Maybe.nothing();
                    }

                    if (!maybe.isPresent()) {
                        out.println("null");
                    } else {
                        try {
                            out.println(maybe.get() * maybe.get());
                        } catch (NothingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}