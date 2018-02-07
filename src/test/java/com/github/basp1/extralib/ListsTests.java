package com.github.basp1.extralib;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListsTests {
    @Test
    public void testBinarySearch() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 8, 9, 10);

        assertFalse(Lists.binarySearch(list, -10).isPresent());

        assertTrue(Lists.binarySearch(list, 0).isPresent());

        assertTrue(Lists.binarySearch(list, 4).isPresent());

        assertTrue(Lists.binarySearch(list, 5).isPresent());

        assertTrue(Lists.binarySearch(list, 9).isPresent());

        assertFalse(Lists.binarySearch(list, 7).isPresent());

        assertFalse(Lists.binarySearch(list, 100).isPresent());
    }

    @Test
    public void testLowerBound() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 8, 9, 10);

        assertEquals(0, Lists.lowerBound(list, -10));

        assertEquals(0, Lists.lowerBound(list, 0));

        assertEquals(4, Lists.lowerBound(list, 4));

        assertEquals(5, Lists.lowerBound(list, 5));

        assertEquals(8, Lists.lowerBound(list, 9));

        assertEquals(7, Lists.lowerBound(list, 7));

        assertEquals(9, Lists.lowerBound(list, 10));

        assertEquals(10, Lists.lowerBound(list, 100));
    }

    @Test
    public void testMedian() {
        List<Double> list = Arrays.asList();
        assertEquals(Double.NaN, (double) Lists.median(list), 1e-8);

        list = Arrays.asList(10d);
        assertEquals(10d, (double) Lists.median(list), 1e-8);

        list = Arrays.asList(1d, 2d);
        assertEquals(1.5d, (double) Lists.median(list), 1e-8);

        list = Arrays.asList(1d, 2d, 3d);
        assertEquals(2d, (double) Lists.median(list), 1e-8);

        list = Arrays.asList(1d, 2d, 3d, 4d);
        assertEquals(2.5d, (double) Lists.median(list), 1e-8);

        list = Arrays.asList(3d, 1d, 2d, 4d, 5d);
        assertEquals(3d, (double) Lists.median(list), 1e-8);
    }
}