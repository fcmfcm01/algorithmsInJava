package com.fcm.learning.leetcode;

import java.util.Arrays;

public class Sudoku {

  public void solveSudoku(char[][] board) {
    backTrace(board, 0, 0);
    for (char[] chars : board) {
      System.out.println(Arrays.toString(chars));
    }
  }

  public boolean backTrace(char[][] board, int row, int col) {

    int m = board.length;
    int n = board.length;
    if (col == n) {
      //当前行结束了，进入下一行
      return backTrace(board, row + 1, 0);
    }
    if (row == m) {
      return true;
    }
    //跳过已有的数字
    if (board[row][col] != '.') {
      return backTrace(board, row, col + 1);
    }
    for (char ch = '1'; ch <= '9'; ch++) {
      if (!isValid(board, row, col, ch)) {
        continue;
      }
      board[row][col] = ch;
      if (backTrace(board, row, col + 1)) {
        return true;
      }
      board[row][col] = '.';
    }
    return false;
  }

  boolean isValid(char[][] board, int row, int col, char val) {
    int subBoxSize = Math.round(Math.round(Math.sqrt(board.length)));
    for (int i = 0; i < 9; i++) {
      // 判断行是否存在重复
      if (board[row][i] == val) return false;
      // 判断列是否存在重复
      if (board[i][col] == val) return false;
      // 判断 3 x 3 方框是否存在重复
      if (board[(row / subBoxSize) * subBoxSize + i / subBoxSize][(col / subBoxSize) * subBoxSize + i % subBoxSize] == val)
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Sudoku sudoku = new Sudoku();
    char[][] board = new char[9][9];
    StringBuilder input = new StringBuilder();
    input.append("5 3 . . 7 . . . .").append("#");
    input.append("6 . . 1 9 5 . . .").append("#");
    input.append(". 9 8 . . . . 6 .").append("#");
    input.append("8 . . . 6 . . . 3").append("#");
    input.append("4 . . 8 . 3 . . 1").append("#");
    input.append("7 . . . 2 . . . 6").append("#");
    input.append(". 6 . . . . 2 8 .").append("#");
    input.append(". . . 4 1 9 . . 5").append("#");
    input.append(". . . . 8 . . 7 9").append("#");

    String[] arr = input.toString().split("#");
    for (int i = 0; i < board.length; i++) {
      String temp = arr[i].replace(" ", "");
      board[i] = temp.toCharArray();
    }
    for (int i = 0; i < board.length; i++) {
      System.out.println(Arrays.toString(board[i]));
    }
    char[][] board1 = new char[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board1[i][j] = board[i][j];
      }
    }

    System.out.println();
    long start = System.currentTimeMillis();
    sudoku.solveSudoku(board);
    System.out.println("回溯：" + (System.currentTimeMillis() - start) + "ms");
    Solution solution = new Solution();
    start = System.currentTimeMillis();
    solution.solveSudoku(board1);
    System.out.println("深度优先：" + (System.currentTimeMillis() - start) + "ms");
  }
}

class Solution {
  //行是否含有当前值
  boolean[][] row = new boolean[9][9];
  //列是否含有当前值
  boolean[][] col = new boolean[9][9];
  //每个3x3方格是否是否含有当前值
  boolean[][][] subBoxes = new boolean[3][3][9];

  public void solveSudoku(char[][] board) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.') {
          int c = board[i][j] - '1';
          //行         列          3x3方格
          row[i][c] = col[j][c] = subBoxes[i / 3][j / 3][c] = true;
        }
      }

    dfs(board, 0, 0);
  }

  boolean dfs(char[][] board, int row, int col) {
    if (col == 9) {
      row += 1;
      col = 0;
    }
    if (row == 9) return true;
    if (board[row][col] != '.') return dfs(board, row, col + 1);
    for (int k = 0; k < 9; k++) {
      //当前值已被填入
      if (this.row[row][k] || this.col[col][k] || subBoxes[row / 3][col / 3][k]) continue;
      //填入当前值,行，列，3x3方格
      this.row[row][k] = this.col[col][k] = subBoxes[row / 3][col / 3][k] = true;
      //记录当前值
      board[row][col] = (char) ((int) ('1') + k);
      //处理当前行下一个数据
      if (dfs(board, row, col + 1)) return true;
      //撤销操作
      board[row][col] = '.';
      this.row[row][k] = this.col[col][k] = subBoxes[row / 3][col / 3][k] = false;
    }
    return false;
  }
}
