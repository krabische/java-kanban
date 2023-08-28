import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int insert;
        int number;

        Manager manager = new Manager();
        Task task;
        Epic epic;
        Subtask subtask;
        String newStatus;
        Scanner scanner = new Scanner(System.in);

        //в цикле я оставил автоматический ввод задач, эпиков и подзадач, это очень легко заменить на автосчитывание,
        //но намного легче с ними проводить проверку. Если понадобится, то я могу их заменить на Scanner, но по заданию
        //понял, что можно оставить их как самотестирование

        while (true) {
            menu();
            insert = scanner.nextInt();
            switch (insert) {
                case 1:
                    manager.printHashTask();
                    manager.printHashEpic();
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
                    subtask = new Subtask("Шурупы", "Купить шурупы");
                    System.out.println("Введите номер эпика, к которому относится подзадача:");
                    number = 3;
                    manager.putSubtask(number, subtask);
                    subtask = new Subtask("Купить ей домик", "на сайте");
                    System.out.println("Введите номер эпика, к которому относится подзадача:");
                    number = 4;
                    manager.putSubtask(number, subtask);
                    subtask = new Subtask("Купить ей корм", "на рынке");
                    System.out.println("Введите номер эпика, к которому относится подзадача:");
                    number = 4;
                    manager.putSubtask(number, subtask);
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
                        task = new Task("Помыть посуду", "убрать после гостей");
                        manager.changeTask(number, task);
                        break;
                    } else if (manager.epics.get(number) != null) {
                        epic = new Epic("Помыть посуду", "убрать после гостей");
                        manager.changeEpic(number, epic);
                        break;
                    } else if (manager.subtasks.get(number) != null) {
                        subtask = new Subtask("Помыть посуду", "убрать после гостей");
                        manager.changeSubtask(number, subtask);
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
                case 9:
                    System.out.println("Введите ID задачи:");
                    number = scanner.nextInt();
                    newStatus = "DONE ✅";
                    if (manager.tasks.get(number) != null) {
                        manager.changeTaskStatus(number, newStatus);
                        System.out.println("Статус задачи обновлен!");
                    } else if (manager.epics.get(number) != null) {
                        System.out.println("Чтобы обновить статус эпика - закройте остальные задачи!");
                    } else if (manager.subtasks.get(number) != null) {
                        manager.changeSubtaskStatus(number, newStatus);
                        System.out.println("Статус задачи обновлен!");
                        manager.checkStatus();
                    }
                    break;
                case 10:
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
                "9. Отметить задачу оконченой " +
                "10. Выход ");
    }
}
