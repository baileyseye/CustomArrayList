package org.baileyseye.custom;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void add() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test");
        assertEquals(1, list.size());
        assertEquals("Test", list.get(0));
    }

    @Test
    void get() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");
        assertEquals("Hello", list.get(0));
    }

    @Test
    void remove() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test1");
        list.add("Test2");
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("Test2", list.get(0));
    }

    @Test
    void size() {
        MyArrayList<String> list = new MyArrayList<>();
        assertEquals(0, list.size());
        list.add("Test");
        assertEquals(1, list.size());
    }

    @Test
    void isEmpty() {
        MyArrayList<String> list = new MyArrayList<>();
        assertTrue(list.isEmpty());
        list.add("Test");
        assertFalse(list.isEmpty());
    }

    @Test
    void contains() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test");
        assertTrue(list.contains("Test"));
        assertFalse(list.contains("Test2"));
    }

    @Test
    void indexOf() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test1");
        list.add("Test2");
        assertEquals(0, list.indexOf("Test1"));
        assertEquals(1, list.indexOf("Test2"));
        assertEquals(-1, list.indexOf("Test3"));
    }

    @Test
    void clear() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test");
        list.add("Test2");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void toArray() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test1");
        list.add("Test2");
        Object[] array = list.toArray();
        assertEquals(2, array.length);
        assertEquals("Test1", array[0]);
        assertEquals("Test2", array[1]);
    }

    @Test
    void addAll() {
        MyArrayList<String> list = new MyArrayList<>();
        ArrayList<String> newList = new ArrayList<>();
        newList.add("Test1");
        newList.add("Test2");
        list.addAll(newList);
        assertEquals(2, list.size());
        assertTrue(list.contains("Test1"));
        assertTrue(list.contains("Test2"));
    }

    @Test
    void removeAll() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
        ArrayList<String> removeList = new ArrayList<>();
        removeList.add("Test1");
        removeList.add("Test3");
        list.removeAll(removeList);
        assertEquals(1, list.size());
        assertTrue(list.contains("Test2"));
    }

    @Test
    void ensureCapacity() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertEquals(100, list.size());
        assertEquals(Integer.valueOf(99), list.get(99));
    }

    @Test
    void indexOfWithNull() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(null);
        list.add("Element");
        assertEquals(0, list.indexOf(null));
    }

    @Test
    void toArrayCorrectSize() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element1");
        Object[] array = list.toArray();
        assertEquals(1, array.length);
    }

    @Test
    void addAllWithNulls() {
        MyArrayList<String> list = new MyArrayList<>();
        list.addAll(Arrays.asList("Element1", null, "Element2"));
        assertEquals(3, list.size());
        assertNull(list.get(1));
    }

    @Test
    void exceptionTesting() {
        MyArrayList<String> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));

        ArrayList<String> addList = new ArrayList<>();
        addList.add("Element");
        assertTrue(list.addAll(addList));
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    void sortNaturalOrder() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);
        list.sort(Comparator.naturalOrder());
        assertEquals("[1, 2, 3, 4]", Arrays.toString(list.toArray()));
    }
   @Test
    void sortWithComparator() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("banana");
        list.add("apple");
        list.add("fig");
        list.add("elderberry");
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        list.sort(lengthComparator);
        assertEquals("[fig, apple, banana, elderberry]", Arrays.toString(list.toArray()));
    }

    @Test
    void setElement() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Old");
        String oldElement = list.set(0, "New");

        assertEquals("Old", oldElement);
        assertEquals("New", list.get(0));
    }

    @Test
    void setElementOutOfBounds() {
        MyArrayList<String> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "Element"));
    }

    @Test
    void iteratorTest() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("First");
        list.add("Second");
        CustomIterator<String> it = list.iterator();

        assertTrue(it.hasNext());
        assertEquals("First", it.next());
        assertTrue(it.hasNext());
        assertEquals("Second", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void addElementInTheMiddle() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("First");
        list.add("Third");
        list.add(1, "Second");

        assertEquals(3, list.size());
        assertEquals("Second", list.get(1));
        assertEquals("Third", list.get(2));
    }



}