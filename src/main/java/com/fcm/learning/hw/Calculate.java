package com.fcm.learning.hw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {

  /**
   * {8+9}
   */
  public static final String VALID_CURLY_PARENTHESES_EXPRESSION = "\\{(\\d)+([+-/*])(\\d)+\\}";
  public static final String VALID_EXPRESSION = "(\\d)+([+-/*])(\\d)+";
  public static final Pattern PATTERN_VALID_EXPRESSION = Pattern.compile(VALID_EXPRESSION);
  /**
   * (8+9)
   */
  public static final String VALID_PARENTHESES_EXPRESSION = "\\((\\d)+([+-/*])(\\d)+\\)";
  /**
   * [8+9]
   */
  public static final String VALID_SQUARE_BRACKETS_EXPRESSION = "\\[(\\d)+([+-/*])(\\d)+\\]";

  public static int calculateParenthesesNumbers(String input) {
    Pattern pattern = Pattern.compile("((\\d)+([+-/*])(\\d)+).*");
    String temp = input;
    Matcher matcher = pattern.matcher(input);
    if (matcher.find()) {
      String matched = matcher.group(1);
      System.out.println(matched);
      temp = temp.replaceFirst(matched, calculateTwoNumber(matched) + "");
      calculateParenthesesNumbers(temp);
    }
    return 0;
  }

  public static int calculateTwoNumber(String input) {
    Pattern pattern = Pattern.compile("(\\d)+([+-/*])(\\d)+");
    Matcher matcher = pattern.matcher(input);
    int result = 0;
    while (matcher.find()) {
      int num1 = Integer.parseInt(matcher.group(1));
      String operator = matcher.group(2);
      int num2 = Integer.parseInt(matcher.group(3));
      switch (operator) {
        case "+":
          return num1 + num2;
        case "-":
          return num1 - num2;
        case "/":
          return num1 / num2;
        case "*":
          return num1 * num2;
        default:
          throw new IllegalArgumentException("invalid input:" + input);
      }
    }
    throw new IllegalArgumentException("invalid input:" + input);
  }

  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    while (scanner.hasNextLine()) {
//      String input = scanner.nextLine();
//      System.out.println("input:" + input);
//
//    }
//    Matcher matcher = Pattern.compile(VALID_PARENTHESES_EXPRESSION).matcher("3+2*{1+2*[-4/(8-6)+7*(8-3)]}");
//    while (matcher.find()) {
//      System.out.println(matcher.group(0));
//    }
    System.out.println(calculateParenthesesNumbers("8+3-4+2-5"));

  }


  //[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’
  public static int solution(String input) {
    String temp = input;
    Matcher matcher = Pattern.compile(VALID_PARENTHESES_EXPRESSION).matcher(input);
    while (matcher.find()) {

    }

    return-1;
}

  public static boolean validExpression(String input) {
    Matcher matcher = PATTERN_VALID_EXPRESSION.matcher(input);
    return matcher.find();
  }
}
