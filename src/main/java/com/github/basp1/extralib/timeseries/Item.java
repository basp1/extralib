package com.github.basp1.extralib.timeseries;

import org.joda.time.DateTime;

public interface Item<T extends Item> {
    DateTime getTime();

    T add(T other);

    T subtract(T other);
}
