package com.solncev.net;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    static String[] controlWords = {"a"};

    public static void main(String[] args) {
        test();
    }

     static void test() {
        String[] words = "a".split(" ");
        Stream<String> wordsStream = Arrays.stream(words);
         for (String controlWord : controlWords) {
             if (wordsStream.noneMatch(Predicate.isEqual(controlWord))) {
                 return;
             }
         }
     }
}
