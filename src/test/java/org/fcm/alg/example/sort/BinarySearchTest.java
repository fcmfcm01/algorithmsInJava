package org.fcm.alg.example.sort;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static org.fcm.alg.example.sort.BinarySearch.search;

public class BinarySearchTest extends TestCase {



    public void testSearch() {
        List<Integer> input = Arrays.asList(new Integer[]{1,3,5,6,7,8,9,20,67});
        Assert.assertEquals(search(input,6),3);
    }
}