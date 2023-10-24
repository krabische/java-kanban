package managers;

import tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private File file;
    private String fileName;

    private HistoryManager historyManager;

    public FileBackedTasksManager(String fileName, HistoryManager historyManager) {
        super(historyManager);
        this.fileName = fileName;
        this.file = new File(fileName);
        this.historyManager = historyManager;
        loadTasksFromFile(fileName);
    }

    public void loadTasksFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean skipFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }

                if (!line.isBlank()) {
                    Task task = fromStringToTask(line);
                    if (task != null && task.getType() == TaskTypes.TASK) {
                        addTask(task);
                    } else if (task != null && task.getType() == TaskTypes.EPIC) {
                        addEpic((Epic) task);
                    } else if (task != null && task.getType() == TaskTypes.SUBTASK) {
                        addSubtask((Subtask) task);
                    }
                }
                if (line.isBlank()) {
                    line = reader.readLine();
                    List<Integer> history = historyFromString(line);
                    for (Integer taskId : history) {
                        if (getTasks().get(taskId) != null) {
                            historyManager.add(getTasks().get(taskId));
                        } else if (getEpics().get(taskId) != null) {
                            historyManager.add(getEpics().get(taskId));
                        } else if (getSubtasks().get(taskId) != null) {
                            historyManager.add(getSubtasks().get(taskId));
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save() {
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

            writer.write(" \n");
            String history = historytoString(historyManager);
            writer.write(history);


        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка записи файла.");
        }

    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);

    }

    @Override
    public Task getTask(Integer id) {
        Task task = super.getTask(id);
        save();
        return task;
    }

    @Override
    public Epic getEpic(Integer id) {
        Epic epic = super.getEpic(id);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtask(Integer id) {
        Subtask subtask = super.getSubtask(id);
        save();
        return subtask;
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

    public Task fromStringToTask(String value) { // при чтении файла
        String[] parts = value.split(",");
        int id = Integer.parseInt(parts[0]);
        TaskTypes type = TaskTypes.valueOf(parts[1]);
        String name = parts[2];
        Status status = Status.valueOf(parts[3]);
        String description = parts[4];
        int epic = 0;
        if (parts.length == 6) {
            epic = Integer.parseInt(parts[5]);
        }
        switch (type) {
            case TASK:
                return new Task(id, name, status, description, epic);
            case SUBTASK:
                return new Subtask(id, name, status, description, epic);
            case EPIC:
                return new Epic(id, name, status, description, epic);
            default:
                System.out.println("Задача не опознана!");
        }
        return null;
    }

    static String historytoString(HistoryManager manager) {
        List<Task> hTasks = manager.getHistory();
        String history = "";
        for (Task h : hTasks) {
            history = history + (h != null ? "," + h.getId() : "");
        }
        return history;
    }

    static List<Integer> historyFromString(String value) {
        List<Integer> historyOfTasks = new ArrayList<>();
        String[] history = value.split(",");
        for (String h : history) {
            if (!h.isEmpty()) {
                historyOfTasks.add(Integer.parseInt(h));
            }
        }
        return historyOfTasks;
    }
}