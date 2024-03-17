package org.baileyseye.custom;

public interface CustomCollection<E> extends CustomIterable<E> {
    boolean add(E element);
    boolean remove(Object o);
    int size();
    boolean isEmpty();
}
