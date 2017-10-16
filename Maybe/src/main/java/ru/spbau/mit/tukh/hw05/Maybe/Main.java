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
     * main method. Tries to read all numbers in file input.txt and for each successful red writes line with its square
     * to output.txt. For other writes null.
     * @param args may contain everything, it doesn't matter.
     * @throws IOException according to files input.txt and output.txt.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        StringTokenizer in;

        String s;
        Integer number;
        Maybe<Integer> mb;

        while ((s = br.readLine()) != null){
            in = new StringTokenizer(s);

            while (in.hasMoreTokens()){
                try{
                    number = Integer.parseInt(in.nextToken());
                    mb = Maybe.just(number);
                } catch (NumberFormatException e){
                    mb = Maybe.nothing();
                }

                if (!mb.isPresent()){
                    out.println("null");
                } else{
                    try {
                        out.println(mb.get() * mb.get());
                    } catch (NothingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        out.close();
        br.close();
    }
}