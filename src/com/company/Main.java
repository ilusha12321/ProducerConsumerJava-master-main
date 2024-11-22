package com.company;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager(5); // Розмір сховища

        int[] producerItems = {4, 3, 5, 4, 2}; // Кількість елементів для кожного виробника
        int[] consumerItems = {4, 3, 5, 6};   // Кількість елементів для кожного споживача

        // Створення потоків виробників
        for (int i = 0; i < producerItems.length; i++) {
            new Producer(producerItems[i], manager, i + 1); // Кожен виробник отримує свій ID і план виробництва
        }

        // Створення потоків споживачів
        for (int i = 0; i < consumerItems.length; i++) {
            new Consumer(consumerItems[i], manager, i + 1); // Кожен споживач отримує свій ID і план споживання
        }
    }
}
