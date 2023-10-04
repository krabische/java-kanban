import managers.*;
import tasks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int insert;
        int number;
        Integer epicId;

        Task task;
        Epic epic;
        Subtask subtask;
        Scanner scanner = new Scanner(System.in);
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        while (true) {
            printMenu();
            insert = scanner.nextInt();
            switch (insert) {
                case 1:
                    ArrayList<Task> tasks = inMemoryTaskManager.getAllTasks();
                    System.out.println("Задачи:");
                    for (Task tasken : tasks) {
                        System.out.println(tasken.toString());
                    }
                    ArrayList<Epic> epics = inMemoryTaskManager.getAllEpics();
                    for (Epic epicen : epics) {
                        System.out.println(epicen.toString());
                        System.out.println("    Подзадачи:");
                        for (Integer subtaskId : epicen.getSubtaskIds()) {
                            Subtask subtasken = inMemoryTaskManager.getSubtasks().get(subtaskId);
                            System.out.println("    " + subtasken.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Задачи созданы!");
                    task = new Task("Убраться", "помыть полы");
                    inMemoryTaskManager.putTask(task);
                    task = new Task("Погулять", "увидеться с Вовой");
                    inMemoryTaskManager.putTask(task);
                    epic = new Epic("Починить телевизор", "сломался экран");
                    inMemoryTaskManager.putEpic(epic);
                    epic = new Epic("Купить собаку", "найти продавца и договориться о встречи");
                    inMemoryTaskManager.putEpic(epic);
                    break;
                case 3:
                    System.out.println("Подзадачи созданы!");
                    epicId = 3;
                    subtask = new Subtask("Шурупы", "Купить шурупы", epicId);
                    inMemoryTaskManager.putSubtask(subtask);
                    epicId = 4;
                    subtask = new Subtask("Купить ей домик", "на сайте", epicId);
                    inMemoryTaskManager.putSubtask(subtask);
                    epicId = 4;
                    subtask = new Subtask("Купить ей корм", "на рынке", epicId);
                    inMemoryTaskManager.putSubtask(subtask);
                    break;
                case 4:
                    inMemoryTaskManager.getTasks().clear();
                    inMemoryTaskManager.getEpics().clear();
                    inMemoryTaskManager.getSubtasks().clear();
                    System.out.println("Все задачи удалены!");
                    break;
                case 5:
                    System.out.print("Введите ID задачи: ");
                    insert = scanner.nextInt();
                    if (inMemoryTaskManager.getTasks().containsKey(insert)) {
                        System.out.println(inMemoryTaskManager.getTask(insert));
                    } else if (inMemoryTaskManager.getEpics().containsKey(insert)) {
                        System.out.println(inMemoryTaskManager.getEpic(insert));
                    } else if (inMemoryTaskManager.getSubtasks().containsKey(insert)) {
                        System.out.println("Подзадача:");
                        System.out.println(inMemoryTaskManager.getSubtask(insert));
                    } else {
                        System.out.println("Такой задачи нет!");
                    }
                    break;
                case 6:
                    System.out.print("Введите ID задачи, которую хотите обновить: ");
                    number = scanner.nextInt();
                    System.out.print("Введите новую задачу: ");
                    if (inMemoryTaskManager.getTasks().get(number) != null) {
                        task = new Task(number, "Помыть посуду", "убрать после гостей", Status.DONE);
                        inMemoryTaskManager.changeTask(task);
                        break;
                    } else if (inMemoryTaskManager.getEpics().get(number) != null) {
                        epic = new Epic(number, "Помыть посуду", "убрать после гостей", Status.DONE);
                        inMemoryTaskManager.changeEpic(epic);
                        break;
                    } else if (inMemoryTaskManager.getSubtasks().get(number) != null) {
                        subtask = new Subtask(number, "Помыть посуду", "убрать после гостей", Status.DONE);
                        inMemoryTaskManager.changeSubtask(subtask);
                        break;
                    }
                    System.out.println("Задача обновлена!");
                    break;
                case 7:
                    System.out.print("Введите ID задачи, которую хотите удалить: ");
                    number = scanner.nextInt();
                    if (inMemoryTaskManager.getTasks().get(number) != null) {
                        inMemoryTaskManager.removeTask(number);
                        break;
                    } else if (inMemoryTaskManager.getEpics().get(number) != null) {
                        inMemoryTaskManager.removeEpic(number);
                        break;
                    } else if (inMemoryTaskManager.getSubtasks().get(number) != null) {
                        inMemoryTaskManager.removeSubtask(number);
                        break;
                    }
                    System.out.println("Задача удалена!");
                    break;
                case 8:
                    System.out.println("Введите номер эпика, у которого хотите просмотреть подзадачи: ");
                    number = scanner.nextInt();
                    System.out.println(inMemoryTaskManager.getSubtasks().get(number));
                    break;
                case 9: {
                    System.out.println("Введите группу задач, которую вы хотите вывести: " +
                            "(1) AllTasks.Task, (2) AllTasks.Epic, (3) AllTasks.Subtask");
                    number = scanner.nextInt();
                    switch (number) {
                        case 1:
                            ArrayList<Task> tasksList = inMemoryTaskManager.getAllTasks();
                            for (Task tasken : tasksList) {
                                System.out.println(tasken.toString());
                            }
                            break;
                        case 2:
                            ArrayList<Epic> epicsList = inMemoryTaskManager.getAllEpics();
                            for (Epic epicen : epicsList) {
                                System.out.println(epicen.toString());
                            }

                            break;
                        case 3:
                            ArrayList<Subtask> subtasksList = inMemoryTaskManager.getAllSubtasks();
                            for (Subtask subtasken : subtasksList) {
                                System.out.println(subtasken.toString());
                            }
                    }
                    break;
                }
                case 10: {
                    System.out.println("Введите группу задач, которую вы хотите удалить: " +
                            "(1) AllTasks.Task, (2) AllTasks.Epic, (3) AllTasks.Subtask");
                    number = scanner.nextInt();
                    switch (number) {
                        case 1:
                            inMemoryTaskManager.removeTasks();
                            break;
                        case 2:
                            inMemoryTaskManager.removeEpics();
                            break;
                        case 3:
                            inMemoryTaskManager.removeSubtasks();
                            break;
                    }
                    break;
                }
                case 11:
                    System.out.println("Введите номер эпика: ");
                    number = scanner.nextInt();
                    ArrayList<Subtask> subtasksList = inMemoryTaskManager.getEpicsSubtasks(number);
                    for (Subtask subtasken : subtasksList) {
                        System.out.println(subtasken.toString());
                    }
                    break;

                case 12:
                    System.out.println("История последних просмотренных задач");
                    List<Task> tasksHistory = inMemoryTaskManager.getHistory();
                    System.out.println(tasksHistory);
                    break;

                case 13:
                    System.out.println("До встречи!");
                    return;
            }
        }
    }

    static void printMenu() {
        System.out.println("Выберите один из пунктов: \n" +
                "1. Посмотреть список задач \n" +
                "2. Добавить задачу \n" +
                "3. Добавить подзадачу \n" +
                "4. Удалить все задачи \n" +
                "5. Найти задачу по ID \n" +
                "6. Обновить задачу \n" +
                "7. Удалить задачу по ID \n" +
                "8. Просмотр подзадач \n" +
                "9. Вывести группу задач \n" +
                "10. Удалить группу задач \n" +
                "11. Вывести подзадачи эпика \n" +
                "12. Вывести историю \n" +
                "13. Выход \n");
    }
}
