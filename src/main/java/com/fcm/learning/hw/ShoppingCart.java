package com.fcm.learning.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ShoppingCart {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = parseInt(str.split(" ")[0]) / 10;
        int m = parseInt(str.split(" ")[1]);
        Item[] items = new Item[m + 1];
        int[][] dp = new int[m + 1][N + 1];
        for (int i = 0; i < m; i++) {
            String[] vals = br.readLine().split("\\s");
            items[i] = new Item(parseInt(vals[0]) / 10, parseInt(vals[1]), parseInt(vals[2]));
        }
        for (int i = 1; i <= m; i++) {
            //从列表中取出item
            Item item = items[i];
            int currentValue = 0;
            if (item.q > 0) {
                //当前为附件时，必须配合主件使用
                // 如果剩余的金额不足以放下当前的item+ 主item
                // 取主item的时候注意index，数据给的index是从1开始的....
                currentValue = item.v + items[item.q].v;

            } else {
                //当前为主件时，可以单独使用
                // 如果剩余的金额不足以放下当前的item
                currentValue = item.v;
            }
            //从item.v开始，只要是不超过给定的money的都是能放进去的最大值
            for (int j = item.v; j <= N; j++) {
                if (currentValue <= j)
                /**和上次已经放了current value 的项比较
                 * 用j这么多钱去买 i件商品 可以获得最大价值
                 * 之所以是用i-1个的值中的较大值+重要性*价值，是因为最新的值是在原来的值上附加的
                 */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.v] + item.p);
            }

        }
        System.out.println(dp[m][N] * 10);

    }
}


class Item {
    int v;
    int p;
    int q;

    public Item(int v, int p, int q) {
        this.v = v;
        this.p = p * v;
        this.q = q;
    }
}