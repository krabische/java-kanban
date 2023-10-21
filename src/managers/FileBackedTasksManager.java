package managers;

import resources.FileReader;
import tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private File file;

    private String fileName;

    public FileBackedTasksManager() {
        this.file = new File("AllTasks.csv");
        loadTasksFromFile();
    }

    public ArrayList<Task> files = new ArrayList<>();

    public FileBackedTasksManager(String fileName) {
        this.file = new File(fileName);
        loadTasksFromFile();
      //  files = fileReader.splitByTasks(fileName);
    }

    public void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = stringToTask(line);
                if (task != null) {
                    putTask(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try (Writer writer = new FileWriter(file)) {
            writer.write("id,type,name,status,description,epic\n");
            HashMap<Integer, String> allTasks = new HashMap<>();

            HashMap<Integer, Task> tasks = super.getTasks();
            for (Integer id : tasks.keySet()) {
                allTasks.put(id, tasks.get(id).toStringFromFile());
            }

            HashMap<Integer, Epic> epics = super.getEpics();
            for (Integer id : epics.keySet()) {
                allTasks.put(id, epics.get(id).toStringFromFile());
            }

            HashMap<Integer, Subtask> subtasks = super.getSubtasks();
            for (Integer id : subtasks.keySet()) {
                allTasks.put(id, subtasks.get(id).toStringFromFile());
            }

            for (String value : allTasks.values()) {
                writer.write(String.format("%s\n", value));
            }


        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка записи файла.");
        }

    }

    @Override
    public void putTask(Task task) {
        super.putTask(task);
        save();
    }

    @Override
    public void putEpic(Epic epic) {
        super.putEpic(epic);
        save();
    }

    @Override
    public void putSubtask(Subtask subtask) {
        super.putSubtask(subtask);
        save();
    }

    @Override
    public void changeEpic(Epic epic) {
        super.changeEpic(epic);
        save();
    }

    @Override
    public void changeTask(Task task) {
        super.changeTask(task);
        save();
    }

    @Override
    public void changeSubtask(Subtask subtask) {
        super.changeSubtask(subtask);
        save();
    }

    @Override
    public void removeTask(Integer taskId) {
        super.removeTask(taskId);
        save();
    }

    @Override
    public void removeTasks() {
        super.removeTasks();
        save();
    }

    @Override
    public void removeEpics() {
        super.removeEpics();
        save();
    }

    @Override
    public void removeSubtasks() {
        super.removeSubtasks();
        save();
    }

    @Override
    public void removeEpic(Integer epicId) {
        super.removeEpic(epicId);
        save();
    }

    @Override
    public void removeSubtask(Integer taskId) {
        super.removeSubtask(taskId);
        save();
    }

    public String toString(Task task) {
        return task.getId() + "," + task.getType() + "," + task.getTaskName() + "," + task.getStatus() +
                "," + task.getDescription() +
                "," + task.getEpic();
    }

    public Task stringToTask(String value){
            String[] parts = value.split(",");
            int id = Integer.parseInt(parts[0]);
            TaskTypes type = TaskTypes.valueOf(parts[1]);
            String name = parts[2];
            Status status = Status.valueOf(parts[3]);
            String description = parts[4];
            String epic = parts[5];
            switch (type){
                case TASK:
                    return new Task(id, name, status, description, epic);
                case SUBTASK:
                    return new Subtask(id,name, status, description, epic);
                case EPIC:
                    return new Epic(id, name, status, description, epic);
                default:
                    System.out.println("Задача не опознана!");
            }
            return null;
    }
}