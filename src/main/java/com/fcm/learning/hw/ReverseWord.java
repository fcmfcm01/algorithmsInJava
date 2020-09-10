package com.fcm.learning.hw;

import java.util.Scanner;

public class ReverseWord {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        if (scanner.hasNextLine()) {
//            System.out.println(reverseWord(scanner.nextLine()));
//        }
        long start = System.currentTimeMillis();
        System.out.println(100000 + ":" + fib2(100000));
        System.out.println(System.currentTimeMillis() - start + "ms");
        start = System.currentTimeMillis();
        System.out.println(100000 + ":" + fib3(100000));
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    public static String reverseWord(String word) {
        String[] words = word.split("[^A-Za-z]+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            stringBuilder.append(words[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }


    public static int fib(int N) {
        if (N < 2) {
            return N;
        }
        int[] memo = new int[N + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= N; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[N];

    }

    public static int fib2(int N) {
        if (N < 2) {
            return N;
        }
        int current = 0;
        int pre1 = 1;
        int pre2 = 0;
        for (int i = 2; i <= N; i++) {
            current = pre1 + pre2;
            pre2 = pre1;
            pre1 = current;
        }
        return current;

    }

    public static int fib3(int N) {
        if (N < 2) {
            return N;
        }
        int[] memo = new int[N + 1];
        return fibHelper(memo, N);
    }

    public static int fibHelper(int[] memo, int N) {
        if (N < 2) {
            return N;
        }
        if (memo[N] != 0) return memo[N];
        memo[N] = fibHelper(memo, N - 1) + fibHelper(memo, N - 2);
        return memo[N];
    }
}
