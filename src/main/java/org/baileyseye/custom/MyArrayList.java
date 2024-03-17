package org.baileyseye.custom;

import java.io.Serializable;
import java.util.*;

public class MyArrayList<E> implements CustomList<E>, Serializable, Cloneable {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elements = new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
        size = 0;
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        E oldElement = get(index);
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (size == elements.length) {
            increaseCapacity(size + 1);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean add(E element) {
        increaseCapacity(size + 1);
        elements[size++] = element;
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        Object[] a = c.toArray();
        int numNew = a.length;
        increaseCapacity(size + numNew);
        System.arraycopy(a, 0, elements, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public E remove(int index) {
        E element = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(o, elements[index])) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    public void removeAll(Collection<?> c) {
        int writeIndex = 0;
        for (int currentIndex = 0; currentIndex < size; currentIndex++) {
            if (!c.contains(elements[currentIndex])) {
                elements[writeIndex++] = elements[currentIndex];
            }
        }

        for (int i = writeIndex; i < size; i++) {
            elements[i] = null;
        }
        size = writeIndex;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
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



    private void increaseCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }



    @Override
    public CustomIterator<E> iterator() {
        return new CustomArrayListIterator();
    }

    @SuppressWarnings("unchecked")
    private class CustomArrayListIterator implements CustomIterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            return (E) elements[currentIndex++];
        }
    }

    public void sort(Comparator<? super E> c) {
        quickSort(0, size - 1, c);
    }

    private void quickSort(int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pi = partition(low, high, c);
            quickSort(low, pi - 1, c);
            quickSort(pi + 1, high, c);
        }
    }
    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<? super E> c) {
        E pivot = (E) elements[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (c.compare((E) elements[j], pivot) <= 0) {
                i++;
                E temp = (E) elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }
        E temp = (E) elements[i + 1];
        elements[i + 1] = elements[high];
        elements[high] = temp;
        return i + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() throws CloneNotSupportedException {
        MyArrayList<E> clonedList = (MyArrayList<E>)super.clone();
        clonedList.elements = Arrays.copyOf(this.elements, this.size);
        return clonedList;
    }

    public MyListIterator<E> myListIterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements MyListIterator<E> {
        private int currentIndex = 0;
        private int lastReturned = -1;

            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                lastReturned = currentIndex;
                return get(currentIndex++);
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                lastReturned = --currentIndex;
                return get(currentIndex);
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (lastReturned < 0) throw new IllegalStateException();
                MyArrayList.this.remove(lastReturned);
                currentIndex = lastReturned;
                lastReturned = -1;
            }

            @Override
            public void set(E e) {
                if (lastReturned < 0) throw new IllegalStateException();
                MyArrayList.this.set(lastReturned, e);
            }

            @Override
            public void add(E e) {
                MyArrayList.this.add(currentIndex++, e);
                lastReturned = -1;
            }
        }
    }

