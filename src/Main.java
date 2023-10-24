import managers.*;

import tasks.*;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int insert;
        int number;

        Task task;
        Epic epic;
        Subtask subtask;
        Scanner scanner = new Scanner(System.in);
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager("/Users/krabische/dev/java-kanban/src/resources/AllTasks.csv", historyManager);

        while (true) {
            printMenu();
            insert = scanner.nextInt();
            switch (insert) {
                case 1:
                    ArrayList<Task> tasks = fileBackedTasksManager.getAllTasks();
                    System.out.println("Задачи:");
                    for (Task tasken : tasks) {
                        try {
                            System.out.println(tasken.toString());
                        } catch (NullPointerException e) {
                            break;
                        }
                    }
                    ArrayList<Epic> epics = fileBackedTasksManager.getAllEpics();
                    for (Epic epicen : epics) {
                        try {
                        System.out.println(epicen.toString());
                        } catch (NullPointerException e) {
                            break;
                        }
                        System.out.println("    Подзадачи:");
                        for (Integer subtaskId : epicen.getSubtaskIds()) {
                            try {
                                Subtask subtasken = fileBackedTasksManager.getSubtasks().get(subtaskId);
                                System.out.println("    " + subtasken.toString());
                            } catch (NullPointerException e){
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Задача добавлена");
                    task = new Task("Убраться", "помыть полы");
                    fileBackedTasksManager.putTask(task);
                    break;
                case 4:
                    fileBackedTasksManager.getTasks().clear();
                    fileBackedTasksManager.getEpics().clear();
                    fileBackedTasksManager.getSubtasks().clear();
                    System.out.println("Все задачи удалены!");
                    break;
                case 5:
                    System.out.print("Введите ID задачи: ");
                    insert = scanner.nextInt();
                    if (fileBackedTasksManager.getTasks().containsKey(insert)) {
                        System.out.println(fileBackedTasksManager.getTask(insert));
                    } else if (fileBackedTasksManager.getEpics().containsKey(insert)) {
                        System.out.println(fileBackedTasksManager.getEpic(insert));
                    } else if (fileBackedTasksManager.getSubtasks().containsKey(insert)) {
                        System.out.println("Подзадача:");
                        System.out.println(fileBackedTasksManager.getSubtask(insert));
                    } else {
                        System.out.println("Такой задачи нет!");
                    }
                    break;
                case 6:
                    System.out.print("Введите ID задачи, которую хотите обновить: ");
                    number = scanner.nextInt();
                    System.out.print("Введите новую задачу: ");
                    if (fileBackedTasksManager.getTasks().get(number) != null) {
                        task = new Task(number, "Помыть посуду", Status.DONE, "убрать после гостей", 0);
                        fileBackedTasksManager.changeTask(task);
                        break;
                    } else if (fileBackedTasksManager.getEpics().get(number) != null) {
                        epic = new Epic(number, "Помыть посуду", Status.DONE, "убрать после гостей", 0);
                        fileBackedTasksManager.changeEpic(epic);
                        break;
                    } else if (fileBackedTasksManager.getSubtasks().get(number) != null) {
                        subtask = new Subtask(number, "Помыть посуду", Status.DONE, "убрать после гостей", fileBackedTasksManager.getSubtasks().get(number).getEpicId());
                        fileBackedTasksManager.changeSubtask(subtask);
                        break;
                    }
                    System.out.println("Задача обновлена!");
                    break;
                case 7:
                    System.out.print("Введите ID задачи, которую хотите удалить: ");
                    number = scanner.nextInt();
                    if (fileBackedTasksManager.getTasks().get(number) != null) {
                        fileBackedTasksManager.removeTask(number);
                        break;
                    } else if (fileBackedTasksManager.getEpics().get(number) != null) {
                        fileBackedTasksManager.removeEpic(number);
                        break;
                    } else if (fileBackedTasksManager.getSubtasks().get(number) != null) {
                        fileBackedTasksManager.removeSubtask(number);
                        break;
                    }
                    System.out.println("Задача удалена!");
                    break;
                case 8:
                    System.out.println("Введите номер эпика, у которого хотите просмотреть подзадачи: ");
                    number = scanner.nextInt();
                    System.out.println(fileBackedTasksManager.getSubtasks().get(number));
                    break;
                case 9: {
                    System.out.println("Введите группу задач, которую вы хотите вывести: " +
                            "(1) AllTasks.Task, (2) AllTasks.Epic, (3) AllTasks.Subtask");
                    number = scanner.nextInt();
                    switch (number) {
                        case 1:
                            ArrayList<Task> tasksList = fileBackedTasksManager.getAllTasks();
                            for (Task tasken : tasksList) {
                                System.out.println(tasken.toString());
                            }
                            break;
                        case 2:
                            ArrayList<Epic> epicsList = fileBackedTasksManager.getAllEpics();
                            for (Epic epicen : epicsList) {
                                System.out.println(epicen.toString());
                            }

                            break;
                        case 3:
                            ArrayList<Subtask> subtasksList = fileBackedTasksManager.getAllSubtasks();
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
                            fileBackedTasksManager.removeTasks();
                            break;
                        case 2:
                            fileBackedTasksManager.removeEpics();
                            break;
                        case 3:
                            fileBackedTasksManager.removeSubtasks();
                            break;
                    }
                    break;
                }
                case 11:
                    System.out.println("Введите номер эпика: ");
                    number = scanner.nextInt();
                    ArrayList<Subtask> subtasksList = fileBackedTasksManager.getEpicsSubtasks(number);
                    for (Subtask subtasken : subtasksList) {
                        System.out.println(subtasken.toString());
                    }
                    break;

                case 12:
                    System.out.println("История последних просмотренных задач");
                    List<Task> tasksHistory = fileBackedTasksManager.getHistory();
                    for (Task t : tasksHistory) {
                        System.out.println(t.toString());
                    }
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
