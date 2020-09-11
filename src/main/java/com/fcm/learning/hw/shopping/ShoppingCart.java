package com.fcm.learning.hw.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

class Item {
  int p;
  int q;
  int v;

  public Item(int v, int p, int q) {
    this.v = v;
    this.p = p * v;
    this.q = q;
  }
}

/**
 * <p>题目描述</p>
 * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * <br/>主件	附件
 * <br/>电脑	打印机，扫描仪
 * <br/>书柜	图书
 * <br/>书桌	台灯，文具
 * <br/>工作椅	无
 * <br/>如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。
 * <br/>他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * <br/>   设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为： * v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
 * <br/>  请你帮助王强设计一个满足要求的购物单。
 */
public class ShoppingCart {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int totalPrice = parseInt(str.split(" ")[0]) / 10;
    int itemType = parseInt(str.split(" ")[1]);
    Item[] items = new Item[itemType + 1];
    int[][] dp = new int[itemType + 1][totalPrice + 1];
    for (int i = 1; i <= itemType; i++) {
      String[] vals = br.readLine().split("\\s");
      items[i] = new Item(parseInt(vals[0]) / 10, parseInt(vals[1]), parseInt(vals[2]));
    }
    Item[] itemInBucket = new Item[itemType + 1];
    for (int i = 1; i <= itemType; i++) {
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
      for (int j = item.v; j <= totalPrice; j++) {
        if (currentValue <= j)
          /*和上次已经放了current value 的项比较
           * 用j这么多钱去买 i件商品 可以获得最大价值
           * 之所以是用i-1个的值中的较大值+重要性*价值，是因为最新的值是在原来的值上附加的
           */
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.v] + item.p);
      }
      itemInBucket[i] = findItemWithP(items, dp[i][totalPrice] - dp[i - 1][totalPrice]);
    }
    System.out.println(dp[itemType][totalPrice] * 10);
    for (int i = 0; i < itemInBucket.length; i++) {
      Item item = itemInBucket[i];
      if (item != null) {
        System.out.println("item:" + findItemIndexWithValue(items, item.v) + ",value:" + item.p + " in bucket");
      }
    }

  }

  private static Item findItemWithP(Item[] items, int value) {
    if (value == 0) {
      return null;
    }
    for (Item item : items) {
      if (item != null && item.p == value) {
        return item;
      }
    }
    return null;
  }

  private static int findItemIndexWithValue(Item[] items, int value) {
    if (value == 0) {
      return -1;
    }
    for (int i = 0; i < items.length; i++) {
      Item item = items[i];
      if (item != null && item.v == value) {
        return i;
      }
    }
    return -1;
  }
}