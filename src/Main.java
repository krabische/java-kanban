import AllManagers.*;
import AllTasks.*;

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
            menu();
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
                            Subtask subtasken = inMemoryTaskManager.subtasks.get(subtaskId);
                            System.out.println("    " + subtasken.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Введите задачу");
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
                    System.out.println("Введите подзадачу:");
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
                    inMemoryTaskManager.tasks.clear();
                    inMemoryTaskManager.epics.clear();
                    inMemoryTaskManager.subtasks.clear();
                    System.out.println("Все задачи удалены!");
                    break;
                case 5:
                    System.out.print("Введите ID задачи: ");
                    insert = scanner.nextInt();
                    if (insert <= inMemoryTaskManager.tasks.size()) {
                        System.out.println(inMemoryTaskManager.getTask(insert));
                    } else {
                        System.out.println(inMemoryTaskManager.getEpic(insert));
                    }
                    if (inMemoryTaskManager.subtasks.containsKey(insert)) {
                        System.out.println("Подзадачи:");
                        System.out.println(inMemoryTaskManager.getSubtask(insert));
                    }
                    break;
                case 6:
                    System.out.print("Введите ID задачи, которую хотите обновить: ");
                    number = scanner.nextInt();
                    System.out.print("Введите новую задачу: ");
                    if (inMemoryTaskManager.tasks.get(number) != null) {
                        task = new Task(number, "Помыть посуду", "убрать после гостей", Status.DONE);
                        inMemoryTaskManager.changeTask(task);
                        break;
                    } else if (inMemoryTaskManager.epics.get(number) != null) {
                        epic = new Epic(number, "Помыть посуду", "убрать после гостей", Status.DONE);
                        inMemoryTaskManager.changeEpic(epic);
                        break;
                    } else if (inMemoryTaskManager.subtasks.get(number) != null) {
                        subtask = new Subtask(number, "Помыть посуду", "убрать после гостей", Status.DONE);
                        inMemoryTaskManager.changeSubtask(subtask);
                        break;
                    }
                    System.out.println("Задача обновлена!");
                    break;
                case 7:
                    System.out.print("Введите ID задачи, которую хотите удалить: ");
                    number = scanner.nextInt();
                    if (inMemoryTaskManager.tasks.get(number) != null) {
                        inMemoryTaskManager.removeTask(number);
                        break;
                    } else if (inMemoryTaskManager.epics.get(number) != null) {
                        inMemoryTaskManager.removeEpic(number);
                        break;
                    } else if (inMemoryTaskManager.subtasks.get(number) != null) {
                        inMemoryTaskManager.removeSubtask(number);
                        break;
                    }
                    System.out.println("Задача удалена!");
                    break;
                case 8:
                    System.out.println("Введите номер эпика, у которого хотите просмотреть подзадачи: ");
                    number = scanner.nextInt();
                    System.out.println(inMemoryTaskManager.subtasks.get(number));
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
                    List<Task> tasksHistory = Managers.getDefaultHistory().getCalledTasks();
                    System.out.println(tasksHistory);
                    break;

                case 13:
                    System.out.println("До встречи!");
                    return;
            }
        }
    }

    static void menu() {
        System.out.println("Выберите один из пунктов: " +
                "1. Посмотреть список задач " +
                "2. Добавить задачу " +
                "3. Добавить подзадачу " +
                "4. Удалить все задачи " +
                "5. Найти задачу по ID " +
                "6. Обновить задачу " +
                "7. Удалить задачу по ID " +
                "8. Просмотр подзадач " +
                "9. Вывести группу задач " +
                "10. Удалить группу задач " +
                "11. Вывести подзадачи эпика " +
                "12. Вывести историю" +
                "13. Выход ");
    }
}
