package com.thyme.mythyme.utils;

import com.thyme.mythyme.models.Messages;

import java.util.Comparator;

public class SortByTimestamp implements Comparator<Messages> {

    public int compare(Messages o1, Messages o2) {
        if (o1.getTimestamp() == null || o2.getTimestamp() == null)
            return 0;
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }

}
