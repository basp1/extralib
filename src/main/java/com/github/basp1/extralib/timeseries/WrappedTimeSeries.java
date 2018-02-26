package com.github.basp1.extralib.timeseries;

import org.joda.time.Interval;

public class WrappedTimeSeries<T extends Item<T>> extends TimeSeries<T> {
    private final Class<T> clazz;
    private TimeSeries<T> orig;

    public WrappedTimeSeries(Class<T> clazz, TimeSeries<T> orig) throws InstantiationException {
        this.clazz = clazz;
        this.orig = orig;
        wrap(orig);
    }

    private WrappedTimeSeries(Class<T> clazz, TimeSeries<T> orig, TimeSeries<T> wrapped) {
        super(wrapped);
        this.clazz = clazz;
        this.orig = orig;
    }

    @Override
    public WrappedTimeSeries<T> interval(Interval interval) {
        return new WrappedTimeSeries<>(clazz, orig.interval(interval), super.interval(interval));
    }

    public void wrap(TimeSeries ts) throws InstantiationException {
        T prev = null;
        try {
            prev = (T) clazz.newInstance();
        } catch (IllegalAccessException e) {
            throw new InstantiationException();
        }

        for (int i = 0; i < orig.size(); i++) {
            T item = orig.get(i);
            add(item.getTime(), item.subtract(prev));
            prev = item;
        }
    }

    public T unwrap(T addition) {
        return orig.last().add(addition);
    }
}