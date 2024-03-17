package org.baileyseye.custom;

public interface CustomList<T> extends CustomCollection<T>{
    T get(int index);
    T set(int index, T element);
    void add(int index, T element);
    T remove(int index);
}
