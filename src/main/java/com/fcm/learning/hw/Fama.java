package com.fcm.learning.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Fama {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str = null;

        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);//有多少种砝码
            String[] s1 = bf.readLine().split(" ");
            String[] s2 = bf.readLine().split(" ");
            int[] weight = new int[s1.length];
            int[] nums = new int[s2.length];
            for (int i = 0; i < n; i++) {
                weight[i] = Integer.parseInt(s1[i]);
                nums[i] = Integer.parseInt(s2[i]);
            }
            Set<Integer> weightSet = new HashSet<>();
            System.out.println(count(n, weight, nums));

        }
    }

    private static int count(int n, int[] weight, int[] nums) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + nums[i] * weight[i];//所有砝码的总重量
        }
        boolean[] weg = new boolean[sum + 1];
        weg[0] = true;//一个砝码都不加
        weg[sum] = true;//所有砝码都加上
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums[i]; j++) {
                for (int k = sum; k >= weight[i]; k--) {
                    if (weg[k - weight[i]]) {//????
                        weg[k] = true;
                    }
                }
            }
        }
        int count = 0;
        for (boolean b : weg) {
            if (b) count++;
        }
        return count;
    }
}
