package com.github.basp1.extralib;

import java.util.List;
import java.util.Optional;

public class Lists {
    public static <T extends Comparable<T>> Optional<T> binarySearch(List<T> list, T item) {
        int l = 0;
        int r = list.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (item.equals(list.get(m))) {
                return Optional.of(list.get(m));
            }

            if (item.compareTo(list.get(m)) < 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return Optional.empty();
    }

    public static <T extends Comparable<T>> int lowerBound(List<T> list, T item) {
        int n = list.size() - 1;
        int l = 0;
        int r = n;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (item.equals(list.get(m))) {
                return m;
            }

            if (item.compareTo(list.get(m)) < 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l;
    }
}