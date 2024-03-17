package org.baileyseye.custom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListIteratorTest {
    private MyArrayList<String> list;
    private MyListIterator<String> iterator;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
        list.add("Aston");
        list.add("Java");
        list.add("Trainee");
        iterator = list.myListIterator();
    }

    @Test
    void testHasNextOnNonEmptyList() {
        assertTrue(iterator.hasNext());
    }

    @Test
    void testNext() {
        assertEquals("Aston", iterator.next());
        assertEquals("Java", iterator.next());
    }

    @Test
    void testHasPreviousAfterNext() {
        iterator.next();
        assertTrue(iterator.hasPrevious());
    }

    @Test
    void testPrevious() {
        iterator.next();
        iterator.next();
        assertEquals("Java", iterator.previous());
    }

    @Test
    void testAdd() {
        iterator.next();
        iterator.add("Test");
        assertEquals("Java", iterator.next());
        iterator.previous();
        assertEquals("Test", iterator.previous());
    }

    @Test
    void testRemove() {
        iterator.next();
        iterator.remove();
        assertFalse(list.contains("Aston"));
        assertEquals(2, list.size());
    }

    @Test
    void testSet() {
        iterator.next(); 
        iterator.set("Test2");
        iterator.previous(); 
        assertEquals("Test2", iterator.next());
    }

    @Test
    void testNextIndexAndPreviousIndex() {
        assertEquals(0, iterator.nextIndex());
        iterator.next();
        assertEquals(1, iterator.nextIndex());
        assertEquals(0, iterator.previousIndex());
    }

    @Test
    void testIllegalStateExceptionOnRemoveWithoutNextOrPrevious() {
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    void testIllegalStateExceptionOnSetWithoutNextOrPrevious() {
        assertThrows(IllegalStateException.class, () -> iterator.set("Kiwi"));
    }
}
