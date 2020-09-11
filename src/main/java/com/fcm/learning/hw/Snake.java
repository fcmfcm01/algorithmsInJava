package com.fcm.learning.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Snake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            printSnake(Integer.parseInt(line));
        }

    }

    public static void printSnake(int nums) {
        StringBuilder stringBuilder = new StringBuilder();
        if (nums < 1) {
            return;
        }
        if (nums < 2) {
            System.out.println(1);
        }
        int[][] arrs = new int[nums][nums];
        for (int i = 0; i < nums; i++) {
            String temp = "";
            for (int j = 0; j < nums - i; j++) {
                if (i + j == 0) {
                    arrs[i][j] = 1;
                }
                if (j > 0 && i == 0) {
                    arrs[i][j] = arrs[i][j - 1] + j + 1;
                }
                if (i > 0) {
                    arrs[i][j] = arrs[i - 1][j] + j + i;
                }
                temp += arrs[i][j] + " ";
            }
            temp = temp.substring(0, temp.length() - 1) + "\n";
            stringBuilder.append(temp);
        }

        System.out.println(stringBuilder.deleteCharAt(stringBuilder.length()-1).toString());
    }

}
