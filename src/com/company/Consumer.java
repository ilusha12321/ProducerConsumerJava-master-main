package com.company;

public class Consumer implements Runnable {
    private final int itemNumbers; // Кількість елементів, які споживає цей потік
    private final Manager manager; // Посилання на спільний менеджер
    private final int consumerId;  // Унікальний ідентифікатор споживача

    public Consumer(int itemNumbers, Manager manager, int consumerId) {
        this.itemNumbers = itemNumbers;
        this.manager = manager;
        this.consumerId = consumerId;

        new Thread(this).start(); // Запуск потоку споживача
    }

    @Override
    public void run() {
        for (int i = 0; i < itemNumbers; i++) {
            try {
                manager.empty.acquire(); // Очікування наявності елементів у сховищі
                Thread.sleep(500); // Затримка для імітації роботи
                manager.access.acquire(); // Блокування доступу до сховища

                String item = manager.storage.remove(0); // Вилучення елемента зі сховища
                System.out.println("Consumer " + consumerId + " consumed: " + item);

                manager.access.release(); // Розблокування сховища
                manager.full.release(); // Сигнал про вільне місце в сховищі
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
