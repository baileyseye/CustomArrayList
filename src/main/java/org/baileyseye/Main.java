package org.baileyseye;

import org.baileyseye.custom.MyArrayList;
import org.baileyseye.custom.MyListIterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = createSampleStringList();
        iterations(list);
        System.out.println("Итерации закончены. \n" +
                "список :");
        printAllElements(list);

    }

    private static void iterations(MyArrayList<String> list) {
        MyListIterator<String> iterator = list.myListIterator();

        System.out.println("Прямой обход:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Обратный обход:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    private static MyArrayList<String> createSampleStringList() {
        MyArrayList<String> list = new MyArrayList<>(20);
        list.add("Aston");
        list.add("Philyas");
        list.add("Baileys");
        return list;
    }

    public static void printAllElements(MyArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}