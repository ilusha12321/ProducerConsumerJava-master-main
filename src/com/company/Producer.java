package com.company;

public class Producer implements Runnable {
    private final int itemNumbers; // Кількість елементів, які виробляє цей потік
    private final Manager manager; // Посилання на спільний менеджер
    private final int producerId;  // Унікальний ідентифікатор виробника

    public Producer(int itemNumbers, Manager manager, int producerId) {
        this.itemNumbers = itemNumbers;
        this.manager = manager;
        this.producerId = producerId;

        new Thread(this).start(); // Запуск потоку виробника
    }

    @Override
    public void run() {
        for (int i = 0; i < itemNumbers; i++) {
            try {
                manager.full.acquire(); // Очікування місця в сховищі
                manager.access.acquire(); // Блокування доступу до сховища

                String item = "Item " + i + " from Producer " + producerId;
                manager.storage.add(item); // Додавання елемента до сховища
                System.out.println("Producer " + producerId + " added: " + item);

                manager.access.release(); // Розблокування сховища
                manager.empty.release(); // Сигнал про доступний елемент
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
