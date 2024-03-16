package org.baileyseye.custom;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10]; // начальный размер массива
        size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size] = element;
        size++;
    }

    private void increaseCapacity() {
        int newSize = elements.length * 2;
        Object[] newElements = new Object[newSize];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        @SuppressWarnings("unchecked")
        T element = (T) elements[index];
        return element;
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // clear to let GC do its work
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew);
        System.arraycopy(a, 0, elements, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            increaseCapacity(minCapacity);
        }
    }

    private void increaseCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(i);
                i--; // После удаления элемента индексы сдвигаются, следовательно, уменьшаем i
                modified = true;
            }
        }
        return modified;
    }
}
