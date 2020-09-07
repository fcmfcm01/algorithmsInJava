package com.fcm.learning.exercises;

public class CountingInversions {
    public static void main(String[] args) {
//        Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
        Comparable[] a={ 1, 20, 6, 4, 5 };
        CountingInversions s = new CountingInversions();
        System.out.println(s.inverseionCount(a));
    }

    /**
     * @param a
     * @param aux
     * @param lo
     * @param mid sub-array left is [lo, mid], sub-array right is [mid + 1, hi]
     * @param hi
     */
    private int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        //Copy to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //i: idx of left part, j: idx of right part, k: idx of the merged one.
        int i = lo, j = mid + 1, count = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)//Left part is consumed
                a[k] = aux[j++];
            else if (j > hi)//Right part is consumed
                a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0) { //left side is bigger
                a[k] = aux[j++];

                //This is the most important part to count the inversions.
                //If aux[i] > aux[j], then following items in left part are all > aux[j].
                //So for aux[j], there are totally (mid - i + 1) reversed items: aux[i], aux[i + 1], ..., aux[mid] > aux[j],
                //since at this stage, sub-array [lo, mid], [mid + 1, hi] are sorted repectively.
                count += mid - i + 1;

            } else { //right side is bigger
                a[k] = aux[i++];
            }
        }
        return count;
    }

    private int sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int count1 = sort(a, aux, lo, mid);
        int count2 = sort(a, aux, mid + 1, hi);
        int count3 = merge(a, aux, lo, mid, hi);
        return count1 + count2 + count3;
    }

    public int inverseionCount(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        return sort(a, aux, 0, a.length - 1);
    }

}
