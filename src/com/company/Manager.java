package com.company;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Manager {
    public Semaphore access; // Семафор для контролю доступу до сховища
    public Semaphore full;   // Семафор для контролю заповнення сховища
    public Semaphore empty;  // Семафор для контролю наявності елементів

    public ArrayList<String> storage = new ArrayList<>(); // Сховище елементів

    public Manager(int storageSize) {
        access = new Semaphore(1);        // Доступ до сховища (один потік одночасно)
        full = new Semaphore(storageSize); // Максимальна кількість місць у сховищі
        empty = new Semaphore(0);         // Кількість доступних елементів
    }
}
