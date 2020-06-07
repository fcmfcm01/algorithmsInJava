package org.fcm.alg.example.binary;


import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;

public class BinarySearch {

    public static int search(List<Integer> input, @NonNull Integer wanted) {
        Assert.isTrue(input != null && input.size() > 0);
        if (input.size() == 1) {
            return input.get(0);
        } else {
            int low = 0;
            int high = input.size() - 1;
            while (low < high) {
                int mid = (low + high) / 2;
                if (wanted == input.get(mid)) {
                    return mid;
                } else if (wanted > input.get(mid)) {
                    low = low + 1;
                } else {
                    high = high - 1;
                }
            }
        }
        return 0;
    }


}
