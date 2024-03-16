package org.baileyseye;

import org.baileyseye.custom.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Aston");
        list.add("Philyas");
        list.add("Baileys");

        System.out.println("Содержит 'Aston': " + list.contains("Aston"));
        System.out.println("Индекс 'Philyas': " + list.indexOf("Philyas"));
        System.out.println("Элемент на позиции 2: " + list.get(2));

        list.remove(1);
        System.out.println("После удаления 'Philyas', содержит 'Philyas': " + list.contains("Philyas"));

        System.out.println("Размер списка: " + list.size());

        list.clear();
        System.out.println("После очистки, пустой: " + list.isEmpty());
    }
}