package org.fcm.alg.example.sort;

public class SelectionSort {
    public static int findSmallest(int[] input){
        int smallestIndex = 0;
        int smallest = input[0];
        for (int i = 0; i < input.length; i++) {
            if(input[i]<smallest){
                smallest= input[i];
                smallestIndex=i;
            }
        }
        return smallestIndex;
    }

    public static int[] selectionSort(int[] input){
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int smallest = findSmallest(input);
            result[i]= input[smallest];
            input[smallest]=Integer.MAX_VALUE;
        }
        return result;
    }
}
