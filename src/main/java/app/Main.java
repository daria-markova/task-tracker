package app;

import service.TaskService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService service = new TaskService();

        while (true) {
            System.out.println("\n1 - Добавить задачу");
            System.out.println("2 - Показать задачи");
            System.out.println("3 - Выполнено");
            System.out.println("4 - Удалить задачу");
            System.out.println("0 - Выход");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите задачу: ");
                    String title = scanner.nextLine();
                    service.addTask(title);
                    break;

                case 2:
                    service.printTasks();
                    break;

                case 3:
                    System.out.println("Введите номер задачи: ");
                    int doneIndex = scanner.nextInt();
                    service.markDone(doneIndex);
                    break;

                case 4:
                    System.out.println("Введите номер задачи: ");
                    int deleteIndex = scanner.nextInt();
                    service.deleteTask(deleteIndex);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Неверный ввод");
            }
        }
    }
}
