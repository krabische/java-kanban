import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int insert;
        int number;
        Integer epicId;

        Manager manager = new Manager();
        Task task;
        Epic epic;
        Subtask subtask;
        Scanner scanner = new Scanner(System.in);

        //в цикле я оставил автоматический ввод задач, эпиков и подзадач, это очень легко заменить на автосчитывание,
        //но намного легче с ними проводить проверку. Если понадобится, то я могу их заменить на Scanner, но по заданию
        //понял, что можно оставить их как самотестирование

        while (true) {
            menu();
            insert = scanner.nextInt();
            switch (insert) {
                case 1:
                    ArrayList<Task> tasks = manager.getAllTasks();
                    System.out.println("Задачи:");
                    for (Task tasken : tasks) {
                        System.out.println(tasken.toString());
                    }
                    ArrayList<Epic> epics = manager.getAllEpics();
                    for (Epic epicen : epics) {
                        System.out.println(epicen.toString());
                        System.out.println("    Подзадачи:");
                        for (Integer subtaskId : epicen.subtaskIds) {
                            Subtask subtasken = manager.subtasks.get(subtaskId);
                            System.out.println("    " + subtasken.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Введите задачу");
                    task = new Task("Убраться", "помыть полы");
                    manager.putTask(task);
                    task = new Task("Погулять", "увидеться с Вовой");
                    manager.putTask(task);
                    epic = new Epic("Починить телевизор", "сломался экран");
                    manager.putEpic(epic);
                    epic = new Epic("Купить собаку", "найти продавца и договориться о встречи");
                    manager.putEpic(epic);
                    break;
                case 3:
                    System.out.println("Введите подзадачу:");
                    epicId = 3;
                    subtask = new Subtask("Шурупы", "Купить шурупы", epicId);
                    manager.putSubtask(subtask);
                    epicId = 4;
                    subtask = new Subtask("Купить ей домик", "на сайте", epicId);
                    manager.putSubtask(subtask);
                    epicId = 4;
                    subtask = new Subtask("Купить ей корм", "на рынке", epicId);
                    manager.putSubtask(subtask);
                    break;
                case 4:
                    manager.tasks.clear();
                    manager.epics.clear();
                    manager.subtasks.clear();
                    System.out.println("Все задачи удалены!");
                    break;
                case 5:
                    System.out.print("Введите ID задачи: ");
                    insert = scanner.nextInt();
                    if (insert <= manager.tasks.size()) {
                        System.out.println(manager.tasks.get(insert));
                    } else {
                        System.out.println(manager.epics.get(insert));
                    }
                    if (manager.subtasks.containsKey(insert)) {
                        System.out.println("Подзадачи:");
                        System.out.println(manager.subtasks.get(insert));
                    }
                    break;
                case 6:
                    System.out.print("Введите ID задачи, которую хотите обновить: ");
                    number = scanner.nextInt();
                    System.out.print("Введите новую задачу: ");
                    if (manager.tasks.get(number) != null) {
                        task = new Task(number, "Помыть посуду", "убрать после гостей", "DONE");
                        manager.changeTask(task);
                        break;
                    } else if (manager.epics.get(number) != null) {
                        epic = new Epic(number, "Помыть посуду", "убрать после гостей", "DONE");
                        manager.changeEpic(epic);
                        break;
                    } else if (manager.subtasks.get(number) != null) {
                        subtask = new Subtask(number, "Помыть посуду", "убрать после гостей", "DONE");
                        manager.changeSubtask(subtask);
                        break;
                    }
                    System.out.println("Задача обновлена!");
                    break;
                case 7:
                    System.out.print("Введите ID задачи, которую хотите удалить: ");
                    number = scanner.nextInt();
                    if (manager.tasks.get(number) != null) {
                        manager.removeTask(number);
                        break;
                    } else if (manager.epics.get(number) != null) {
                        manager.removeEpic(number);
                        break;
                    } else if (manager.subtasks.get(number) != null) {
                        manager.removeSubtask(number);
                        break;
                    }
                    System.out.println("Задача удалена!");
                    break;
                case 8:
                    System.out.println("Введите номер эпика, у которого хотите просмотреть подзадачи: ");
                    number = scanner.nextInt();
                    System.out.println(manager.subtasks.get(number));
                    break;
                case 9: {
                    System.out.println("Введите группу задач, которую вы хотите вывести: " +
                            "(1) Task, (2) Epic, (3) Subtask");
                    number = scanner.nextInt();
                    switch (number) {
                        case 1:
                            ArrayList<Task> tasksList = manager.getAllTasks();
                            for (Task tasken : tasksList) {
                                System.out.println(tasken.toString());
                            }
                            break;
                        case 2:
                            ArrayList<Epic> epicsList = manager.getAllEpics();
                            for (Epic epicen : epicsList) {
                                System.out.println(epicen.toString());
                            }

                            break;
                        case 3:
                            ArrayList<Subtask> subtasksList = manager.getAllSubtasks();
                            for (Subtask subtasken : subtasksList) {
                                System.out.println(subtasken.toString());
                            }
                    }
                    break;
                }
                case 10: {
                    System.out.println("Введите группу задач, которую вы хотите удалить: " +
                            "(1) Task, (2) Epic, (3) Subtask");
                    number = scanner.nextInt();
                    switch (number) {
                        case 1:
                            manager.removeTasks();
                            break;
                        case 2:
                            manager.removeEpics();
                            break;
                        case 3:
                            manager.removeSubtasks();
                            break;
                    }
                    break;
                }
                case 11:
                    System.out.println("Введите номер эпика: ");
                    number = scanner.nextInt();
                    ArrayList<Integer> subtasksList = manager.getEpicsSubtasks(number);
                    for (Integer subtaskId : subtasksList) {
                        subtask = manager.subtasks.get(subtaskId);
                        if (subtask != null) {
                            System.out.println(subtask.toString());
                        }
                    }
                    break;
                case 12:
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
                "12. Выход ");
    }
}
