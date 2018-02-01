package com.github.basp1.extralib;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}