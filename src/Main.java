import managers.*;

import tasks.*;

import java.io.FileNotFoundException;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String fileName = "./src/resources/AllTasks.csv";
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


        System.out.println(fileBackedTasksManager.getTask(1));

        System.out.println("Подзадача:");
        System.out.println(fileBackedTasksManager.getSubtask(6));

        fileBackedTasksManager.removeEpic(3);
        System.out.println("Задача удалена!");

        fileBackedTasksManager.removeSubtask(7);
        System.out.println("Задача удалена!");

        System.out.print("Введите новую задачу: ");
        task = new Task(1, "Помыть посуду", Status.DONE, "убрать после гостей", 0);
        fileBackedTasksManager.changeTask(task);
        System.out.println("Задача обновлена!");

        ArrayList<Task> tasks = fileBackedTasksManager.getAllTasks();
        System.out.println("Задачи:");
        for (Task tasken : tasks) {
                System.out.println(tasken.toString());
        }

        ArrayList<Epic> epics = fileBackedTasksManager.getAllEpics();
        for (Epic epicen : epics) {
            System.out.println(epicen.toString());
            System.out.println("    Подзадачи:");
            for (Integer subtaskId : epicen.getSubtaskIds()) {
                Subtask subtasken = fileBackedTasksManager.getSubtasks().get(subtaskId);
                System.out.println("    " + subtasken.toString());
            }
        }

        System.out.println("\nТЕСТ ЗАГРУЗКА\n");

        fileBackedTasksManager = fileBackedTasksManager.loadTasksFromFile(fileName);

        tasks = fileBackedTasksManager.getAllTasks();
        System.out.println("Задачи:");
        for (Task tasken : tasks) {
            System.out.println(tasken.toString());
        }

        epics = fileBackedTasksManager.getAllEpics();
        for (Epic epicen : epics) {
            System.out.println(epicen.toString());
            System.out.println("    Подзадачи:");
            for (Integer subtaskId : epicen.getSubtaskIds()) {
                Subtask subtasken = fileBackedTasksManager.getSubtasks().get(subtaskId);
                System.out.println("    " + subtasken.toString());
            }
        }


    }

}
