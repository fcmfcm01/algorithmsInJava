package com.fcm.learning.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountString {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(getEnglishCharCount(line));
            System.out.println(getBlankCharCount(line));;
            System.out.println(getNumberCharCount(line));;
            System.out.println(getOtherCharCount(line));;
        }

    }

    /**
     * 统计出英文字母字符的个数。
     *
     * @param str 需要输入的字符串
     * @return 英文字母的个数
     */
    public static int getEnglishCharCount(String str)
    {
        int length = str.length();
        str= str.replaceAll("[a-zA-Z]+","");
        return length-str.length();
    }

    /**
     * 统计出空格字符的个数。
     *
     * @param str 需要输入的字符串
     * @return 空格的个数
     */
    public static int getBlankCharCount(String str)
    {   int length = str.length();
        str= str.replaceAll("\\s+","");
        return length-str.length();
    }

    /**
     * 统计出数字字符的个数。
     *
     * @param str 需要输入的字符串
     * @return 英文字母的个数
     */
    public static int getNumberCharCount(String str)
    {
        int length = str.length();
        str= str.replaceAll("[0-9]+","");
        return length-str.length();
    }

    /**
     * 统计出其它字符的个数。
     *
     * @param str 需要输入的字符串
     * @return 英文字母的个数
     */
    public static int getOtherCharCount(String str)
    {
        str= str.replaceAll("[a-zA-Z]+","");
        str= str.replaceAll("\\s+","");
        str= str.replaceAll("[0-9]+","");
        return str.length();
    }


}
