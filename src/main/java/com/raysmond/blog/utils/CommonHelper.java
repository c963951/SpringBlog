package com.raysmond.blog.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Brian on 21.12.2017.
 */
public class CommonHelper {

    public static
    <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }

}
