package app;

import model.enums.Priority;
import service.TaskService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService service = new TaskService();

        while (true) {
            System.out.println("\n1 - Enter task");
            System.out.println("2 - Show tasks");
            System.out.println("3 - Start task");
            System.out.println("4 - Mark as done");
            System.out.println("5 - Delete task");
            System.out.println("0 - Exit");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter task title:");
                    String title = scanner.nextLine();

                    System.out.println("Select priority:");
                    System.out.println("1 - Low");
                    System.out.println("2 - Medium");
                    System.out.println("3 - High");

                    int pr = scanner.nextInt();
                    scanner.nextLine();

                    Priority priority;

                    switch (pr) {
                        case 1 -> priority = Priority.LOW;
                        case 3 -> priority = Priority.HIGH;
                        default -> priority = Priority.MEDIUM;
                    }

                    service.addTask(title, priority);
                    break;

                case 2:
                    service.printTasks();
                    break;

                case 3:
                    System.out.println("Enter task id to start:");
                    int startId = scanner.nextInt();
                    scanner.nextLine();

                    service.startTask(startId);
                    break;

                case 4:
                    System.out.println("Enter task id to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    service.deleteTask(deleteId);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Incorrect input");
            }
        }
    }
}
