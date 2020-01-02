package com.vova_cons.hundread_games.asteroids.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class ListUtils {
    public static <T> List<T> filter(List<T> list, Filter<T> filter) {
        return filter(list, filter, new LinkedList<T>());
    }

    public static <T> List<T> filter(List<T> list, Filter<T> filter, List<T> result) {
        for(T val : list) {
            if (filter.isApply(val)) {
                result.add(val);
            }
        }
        return result;
    }


    public interface Filter<T> {
        boolean isApply(T value);
    }
}
