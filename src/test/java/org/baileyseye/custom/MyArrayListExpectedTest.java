package org.baileyseye.custom;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
public class MyArrayListExpectedTest {


    @Test
    void workWithEmptyList() {
        MyArrayList<String> list = new MyArrayList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void addElements() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");
        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
    }

    @Test
    void removeElementsFromList() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");

        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("Element3", list.get(1));

        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("Element3", list.get(0));

        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    void handleNullValues() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(null);
        assertTrue(list.contains(null));
        assertEquals(1, list.size());
        assertNull(list.get(0));

        list.add("NotNull");
        assertEquals("NotNull", list.get(1));
        assertEquals(2, list.size());

        list.remove(0);
        assertFalse(list.contains(null));
        assertEquals("NotNull", list.get(0));
    }

    @Test
    void clearList() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void addAllElementsFromCollection() {
        MyArrayList<String> list = new MyArrayList<>();
        ArrayList<String> newElements = new ArrayList<>();
        newElements.add("Element1");
        newElements.add("Element2");

        list.addAll(newElements);
        assertEquals(2, list.size());
        assertTrue(list.contains("Element1"));
        assertTrue(list.contains("Element2"));
    }

    @Test
    void removeAllElementsFromCollection() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");

        ArrayList<String> removeElements = new ArrayList<>();
        removeElements.add("Element1");
        removeElements.add("Element3");

        list.removeAll(removeElements);
        assertEquals(1, list.size());
        assertTrue(list.contains("Element2"));
    }

    @Test
    void addBeyondInitialCapacity() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        assertEquals(15, list.size());
        assertEquals(Integer.valueOf(14), list.get(14));
    }

    @Test
    void indexOfNonExistentElement() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element");
        assertEquals(-1, list.indexOf("NonExistent"));
    }

    @Test
    void removeAllWithTmptyCollection() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element");
        list.removeAll(Collections.emptyList());
        assertEquals(1, list.size());
    }

    @Test
    void removeNonExistentElement() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Element");
        assertFalse(list.remove("NonExistent"));
    }

    @Test
    void addAllWithNullCollection() {
        MyArrayList<String> list = new MyArrayList<>();
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    void getWithInvalidIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void sortEmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.sort(Comparator.naturalOrder());

        assertTrue(list.isEmpty());
    }

    @Test
    void sortSingleElementList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.sort(Comparator.naturalOrder());

        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
    }

}
