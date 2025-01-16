package org.xpertss.ssh.utils;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Lists {

    /**
     * Returns {@code true} if one or more elements satisfy the predicate.
     *
     * @throws NullPointerException If elements or predicate are {@code null}
     */
    public static <T> boolean any(Predicate<? super T> predicate, List<T> elements)
    {
        Objects.requireNonNull(predicate, "predicate");
        for(T item : Objects.requireNonNull(elements, "elements")) {
            if(predicate.test(item)) return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if every element satisfies the predicate. If
     * {@code elements} is empty, {@code true} is returned.
     *
     * @throws NullPointerException If elements or predicate are {@code null}
     */
    public static <T> boolean all(Predicate<? super T> predicate, List<T> elements)
    {
        Objects.requireNonNull(predicate, "predicate");
        for(T item : Objects.requireNonNull(elements, "elements")) {
            if(!predicate.test(item)) return false;
        }
        return true;
    }

    /**
     * Returns {@code true} if none of the elements satisfy the predicate.
     * If {@code elements} is empty, {@code true} is returned.
     *
     * @throws NullPointerException If elements or predicate are {@code null}
     */
    public static <T> boolean none(Predicate<? super T> predicate, List<T> elements)
    {
        Objects.requireNonNull(predicate, "predicate");
        for(T item : Objects.requireNonNull(elements, "elements")) {
            if(predicate.test(item)) return false;
        }
        return true;
    }

}
