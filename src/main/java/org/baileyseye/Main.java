package org.baileyseye;

import org.baileyseye.custom.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = getStringMyArrayList();
        printAllElements(list);
    }

    private static MyArrayList<String> getStringMyArrayList() {
        MyArrayList<String> list = new MyArrayList<>();
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