package org.fcm.alg.example;

import junit.framework.TestCase;
import org.fcm.alg.example.sort.SelectionSort;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortTest extends TestCase {

    public void testSelectionSort() {
        int[] input = new int[10];
        for (int i = 0; i < input.length; i++) {
            Random random = new Random();
            input[i]= random.nextInt(100);
        }
        System.out.println(Arrays.toString(input));
        System.out.println(Arrays.toString(SelectionSort.selectionSort(input)));

    }
}