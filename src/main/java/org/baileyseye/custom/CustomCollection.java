package org.baileyseye.custom;

public interface CustomCollection<T> extends CustomIterable<T> {
    boolean add(T element);

    boolean remove(Object o);

    int size();

    boolean isEmpty();
}
