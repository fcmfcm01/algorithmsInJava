package pkg;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(roundValue(scanner.nextDouble()));
        }
    }

    public static int roundValue(double input) {
        String value = String.valueOf(input);
        int dotIndex = value.indexOf(".");
        System.out.println(Math.round(input));
        if (dotIndex == -1) {
            return Double.valueOf(input).intValue();
        }
        int intPart = Integer.valueOf(value.substring(0, dotIndex));
        int dotPart = Integer.valueOf(value.substring(dotIndex + 1, dotIndex + 2));
        if (dotPart >= 5) {
            return ++intPart;
        } else {
            return intPart;
        }

    }

    public static String factor(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 2; i < Math.sqrt(num); ) {
            if (num % i == 0) {
                sb.append(i).append(" ");
                num /= i;
            } else {
                i++;
            }
        }
        return sb.append(num).append(" ").toString();
    }
}
