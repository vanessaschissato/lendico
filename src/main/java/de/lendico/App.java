package de.lendico;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        Arrays.asList("Hello", "World")
            .stream()
            .forEach(l -> System.out.println(l));

    }
}
