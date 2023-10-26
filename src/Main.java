import managers.*;

import tasks.*;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "/Users/krabische/dev/java-kanban/src/resources/AllTasks.csv";
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(fileName);

        Task task = new Task("Убраться", "помыть полы");
        fileBackedTasksManager.addTask(task);
        task = new Task("Пропылесосить", "почистить ковер");
        fileBackedTasksManager.addTask(task);

        Epic epic = new Epic("Починить телевизор", "сломался экран");
        fileBackedTasksManager.addEpic(epic);
        epic = new Epic("Купить собаку", "найти продавца и договориться о встречи");
        fileBackedTasksManager.addEpic(epic);


        Subtask subtask = new Subtask("Шурупы", "Купить шурупы", 3);
        fileBackedTasksManager.addSubtask(subtask);

        subtask = new Subtask("Купить ей домик", "на сайте", 4);
        fileBackedTasksManager.addSubtask(subtask);

        subtask = new Subtask("Купить ей корм", "на рынке", 4);
        fileBackedTasksManager.addSubtask(subtask);

        int insert = 1;
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

        insert = 6;
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

        int number = 3;
        if (fileBackedTasksManager.getTasks().get(number) != null) {
            fileBackedTasksManager.removeTask(number);
        } else if (fileBackedTasksManager.getEpics().get(number) != null) {
            fileBackedTasksManager.removeEpic(number);
        } else if (fileBackedTasksManager.getSubtasks().get(number) != null) {
            fileBackedTasksManager.removeSubtask(number);
        }
        System.out.println("Задача удалена!");

        number = 7;
        if (fileBackedTasksManager.getTasks().get(number) != null) {
            fileBackedTasksManager.removeTask(number);
        } else if (fileBackedTasksManager.getEpics().get(number) != null) {
            fileBackedTasksManager.removeEpic(number);
        } else if (fileBackedTasksManager.getSubtasks().get(number) != null) {
            fileBackedTasksManager.removeSubtask(number);
        }
        System.out.println("Задача удалена!");

        number = 1;
        System.out.print("Введите новую задачу: ");
        if (fileBackedTasksManager.getTasks().get(number) != null) {
            task = new Task(number, "Помыть посуду", Status.DONE, "убрать после гостей", 0);
            fileBackedTasksManager.changeTask(task);
        } else if (fileBackedTasksManager.getEpics().get(number) != null) {
            epic = new Epic(number, "Помыть посуду", Status.DONE, "убрать после гостей", 0);
            fileBackedTasksManager.changeEpic(epic);
        } else if (fileBackedTasksManager.getSubtasks().get(number) != null) {
            subtask = new Subtask(number, "Помыть посуду", Status.DONE, "убрать после гостей", fileBackedTasksManager.getSubtasks().get(number).getEpicId());
            fileBackedTasksManager.changeSubtask(subtask);
        }
        System.out.println("Задача обновлена!");

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
                } catch (NullPointerException e) {
                    break;
                }
            }
        }

        System.out.println("\nТЕСТ ЗАГРУЗКА\n");

        fileBackedTasksManager = fileBackedTasksManager.loadTasksFromFile(fileName);

        tasks = fileBackedTasksManager.getAllTasks();
        System.out.println("Задачи:");
        for (Task tasken : tasks) {
            try {
                System.out.println(tasken.toString());
            } catch (NullPointerException e) {
                break;
            }
        }

        epics = fileBackedTasksManager.getAllEpics();
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
                } catch (NullPointerException e) {
                    break;
                }
            }
        }


    }

}
