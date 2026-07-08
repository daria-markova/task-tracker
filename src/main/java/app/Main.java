package app;

import model.enums.Priority;
import model.enums.Status;
import service.TaskService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService service = new TaskService();

        service.loadTasks();

        while (true) {
            System.out.println("\n===== TASK TRACKER =====");
            System.out.println("\n1 - Add task");
            System.out.println("2 - Show all tasks");
            System.out.println("3 - Start task");
            System.out.println("4 - Mark as done");
            System.out.println("5 - Delete task");
            System.out.println("6 - Show to do tasks");
            System.out.println("7 - Show in progress tasks");
            System.out.println("8 - Show done tasks");
            System.out.println("9 - Show High priority tasks");
            System.out.println("10 - Search tasks by title");
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

                            System.out.println("Enter deadline (yyyy-MM-dd):");
                            LocalDate deadline = LocalDate.parse(scanner.nextLine());

                            service.addTask(title, priority, deadline);
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
                            System.out.println("Enter task id to mark as done:");
                            int doneId = scanner.nextInt();
                            scanner.nextLine();

                            service.markDone(doneId);
                            break;

                        case 5:
                            System.out.println("Enter task id to delete:");
                            int deleteId = scanner.nextInt();
                            scanner.nextLine();

                            service.deleteTask(deleteId);
                            break;

                        case 6:
                            service.showByStatus(Status.TODO);
                            break;

                        case 7:
                            service.showByStatus(Status.IN_PROGRESS);
                            break;

                        case 8:
                            service.showByStatus(Status.DONE);
                            break;

                        case 9:
                            service.showByPriority(Priority.HIGH);
                            break;

                        case 10:
                            System.out.println("Enter keyword:");
                            String keyword = scanner.nextLine();

                            service.searchByTitle(keyword);
                            break;

                        case 0:
                            return;

                        default:
                            System.out.println("Incorrect input");
                    }

            }
        }
    }

