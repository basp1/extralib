package com.github.basp1.extralib.timeseries;

import com.github.basp1.extralib.Lists;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class TimeSeries<T extends Item<T>> {
    protected final List<DateTime> times;
    protected final List<T> items;

    public TimeSeries(TimeSeries<T> other) {
        this.times = other.times;
        this.items = other.items;
    }

    public TimeSeries() {
        this.times = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void add(DateTime time, T item) {
        times.add(time);
        items.add(item);
    }

    public T get(DateTime time) {
        int index = Lists.lowerBound(times, time);
        return get(index);
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    public T first() {
        return get(0);
    }

    public T last() {
        return get(size() - 1);
    }

    public TimeSeries<T> interval(Interval interval) {
        TimeSeries<T> ts = new TimeSeries<>();

        int index = Lists.lowerBound(times, interval.getStart());

        int n = size();
        for (int i = index; i < n && times.get(i).isBefore(interval.getEnd()); i++) {
            ts.add(times.get(i), items.get(i));
        }

        return ts;
    }

    public List<TimeSeries<T>> split(Period period) {
        List<TimeSeries<T>> result = new ArrayList<>();

        DateTime startTime = times.get(0);
        DateTime endTime = times.get(times.size() - 1);

        for (DateTime current = endTime; current.isAfter(startTime); current = current.minus(period)) {
            DateTime prevTime = current.minus(period).plus(1);
            TimeSeries<T> ts = interval(new Interval(prevTime, current.plus(1)));
            if (ts.size() > 0) {
                result.add(ts);
            }
        }

        Collections.reverse(result);

        return result;
    }

    public <R> Stream<R> map(Function<T, R> func) {
        return items.stream().map(func);
    }
}