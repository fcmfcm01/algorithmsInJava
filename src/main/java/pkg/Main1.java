package pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1 {

    public static void main(String[] args) throws IOException {

        printBrotherWord();

    }

    public static void printBrotherWord() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = reader.readLine()) != null) {
            String[] args = str.split(" ");

            long start = System.currentTimeMillis();

            String target = args[args.length - 2];
            String sortedTarget = sortString(target);
            Integer brotherWordIndex = Integer.valueOf(args[args.length - 1]) - 1;
            List<String> inputs = new ArrayList<>();
            for (int i = 1; i <= Integer.valueOf(args[0]); i++) {
                String s = args[i];
                if (s.length() == target.length() && !s.equals(target) && sortedTarget.equals(sortString(s))) {
                    inputs.add(s);
                }
            }
            Collections.sort(inputs);
            System.out.println(inputs.size());
            if (inputs.size() > brotherWordIndex) {
                System.out.println(inputs.get(brotherWordIndex));
            }
        }
    }

    private static String sortString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return String.copyValueOf(charArray);
    }

    public static void printCharCount() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        byte[] dict = new byte[128];
        for (int i = 0; i < line.length(); i++) {
            dict[(int) line.charAt(i)] = 1;
        }
        int count = 0;
        for (byte val : dict
        ) {
            if (val == 1) {
                count++;
            }
        }
        System.out.println(count);
    }


    public static void printNonRepeatNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int[] usedNumbers = new int[10];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = line.length() - 1; i >= 0; i--) {
            int num = Integer.valueOf(String.valueOf(line.charAt(i)));
            if (usedNumbers[num] == 0) {
                stringBuilder.append(num);
                usedNumbers[num] = 1;
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public static void printMergedArray() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            int length = Integer.valueOf(line);
            int[] values = new int[length];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; ++i) {
                String[] input = reader.readLine().split(" ");
                int key = Integer.valueOf(input[0]);
                values[key] += Integer.valueOf(input[1]);
            }

            for (int j = 0; j < length; j++) {
                if (values[j] == 0)
                    continue;
                else
                    sb.append(j + " " + values[j]).append(System.lineSeparator());
            }
            System.out.println(sb.toString());
        }
    }


}
