package pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;

public class IP {
    public static void main(String[] args) throws IOException {

        isInSameSubnet();
    }

    public static void isInSameSubnet() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = reader.readLine()) != null) {
            String[] mask = str.split("\\.");
            String[] ip1 = reader.readLine().split("\\.");
            String[] ip2 = reader.readLine().split("\\.");
            int result = 0;
            for (int i = 0; i < 4; i++) {
                int maskSec = Integer.valueOf(mask[i]);
                int ip1Sec = Integer.valueOf(ip1[i]);
                int ip2Sec = Integer.valueOf(ip2[i]);
                if (maskSec < 0 || maskSec > 255 || ip1Sec < 0 || ip1Sec > 255 || ip2Sec < 0 || ip2Sec > 255) {
                    result = 1;
                    break;
                }
                if ((maskSec & ip1Sec) != (maskSec & ip2Sec)) {
                    result = 2;
                }
            }
            System.out.println(result);
        }
    }
}
