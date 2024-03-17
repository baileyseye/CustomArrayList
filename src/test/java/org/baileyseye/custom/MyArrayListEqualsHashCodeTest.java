package org.baileyseye.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListEqualsHashCodeTest {

    @Test
    void testEqualsSameContent() {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(i);
        }
        assertEquals(list1, list2);
    }

    @Test
    void testEqualsDifferentContent() {
        MyArrayList<String> list1 = new MyArrayList<>();
        MyArrayList<String> list2 = new MyArrayList<>();
        list1.add("Test");
        list2.add("Different");
        assertNotEquals(list1, list2);
    }

    @Test
    void testEqualsWithNull() {
        MyArrayList<String> list = new MyArrayList<>();
        assertNotEquals(null, list);
    }

    @Test
    void testHashCodeSameContent() {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(i);
        }
        assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    void testHashCodeDifferentContent() {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>();
        list1.add(1);
        list2.add(2);
        assertNotEquals(list1.hashCode(), list2.hashCode());
    }
}
