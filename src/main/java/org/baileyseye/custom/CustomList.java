package org.baileyseye.custom;

public interface CustomList<E> extends CustomCollection<E>{
    E get(int index);
    E set(int index, E element);
    void add(int index, E element);
    E remove(int index);
}
