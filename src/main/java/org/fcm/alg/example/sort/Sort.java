package org.fcm.alg.example.sort;

import java.util.*;

public class Sort {

    public static void main(String[] args) {
        int[] array = randomInput(10000);
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(bubbleSort(array)));
        System.out.println("bubbleSort:" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        array = randomInput(10000);
//        System.out.println("input:" + Arrays.toString(array));
        System.out.println(Arrays.toString(selectionSort(array)));
        System.out.println("selectionSort:" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        array = randomInput(10000);
        System.out.println(Arrays.toString(insertSort(array)));
        System.out.println("insertSort:" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        array = randomInput(10000);
        mergeSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("mergeSort:" + (System.currentTimeMillis() - start) + "ms");
    }

    public static int[] randomInput(int length) {
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = Math.round(Math.round(Math.random() * 10000));
        }
        return a;
    }

    public static Integer[] randomArray(int length) {
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = Math.round(Math.round(Math.random() * 10000));
        }
        return a;
    }

    public static int[] bubbleSort(int[] array) {

        if (array.length < 2) {
            return array;
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = array.length - 1; j > i; j--) {
                    if (array[j] < array[j - 1]) {
                        int temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
        return array;
    }



    public static int[] selectionSort(int[] array) {
        if (array.length < 2) {
            return array;
        } else {
            for (int i = 0; i < array.length; i++) {
                int least = array[i];
                int leastIndex =i;
                // get min value from left values
                for (int j = i; j < array.length; j++) {
                    if(least>array[j]){
                        least=array[j];
                        leastIndex=j;
                    }
                }
                // swap place for least value and current sorted value
                swapValues(array,i,leastIndex);
            }
        }
        return array;
    }
    public static void swapValues(int[] array, int from, int to){
        int temp = array[to];
        array[to]=array[from];
        array[from]=temp;
    }

    public static int[] insertSort(int[] array) {
        if (array.length < 2) {
            return array;
        } else {
            int j; // 已排序列表下标
            int t; // 待排序元素
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    t = array[i]; // 赋值给待排序元素
                    for (j = i - 1; j >= 0 && array[j] > t; j--) {
                        array[j + 1] = array[j]; // 从后往前遍历已排序列表，逐个和待排序元素比较，如果已排序元素较大，则将它后移
                    }
                    array[j + 1] = t; // 将待排序元素插入到正确的位置
                }
            }

        }

        return array;
    }


    public static void mergeSort(int[] array) {
        int length = array.length;
        if (length < 2) {
            return;
        }
        int mid = length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid - 1);
        int[] right = Arrays.copyOfRange(array, mid, length - 1);
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

}

