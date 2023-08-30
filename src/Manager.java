import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int numberOfTask = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();


    public void putTask(Task task) {
        task.setId(getNumberOfTask());
        tasks.put(task.getId(), task);
    }

    public void putEpic(Epic epic) {
        epic.setId(getNumberOfTask());
        epics.put(epic.getId(), epic);
    }

    public void putSubtask(Subtask subtask) {
        subtask.setId(getNumberOfTask());
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.epicId);
        if (epic != null) {
            epic.subtaskIds.add(subtask.getId());
        }
        updateStatus(subtask.epicId);
    }

    public void changeEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        }
    }

    public void changeTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void changeSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            updateStatus(subtask.epicId);
        }
    }

    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeEpics() {
        removeSubtasks();
        epics.clear();
    }

    public void removeSubtasks() {
        for (Epic epic : epics.values()) {
            epic.subtaskIds.clear();
        }
        subtasks.clear();
    }

    public void removeEpic(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            for (Integer subtaskId : epic.subtaskIds) {
                subtasks.remove(subtaskId);
            }
            epics.remove(epicId);
        }
    }

    public void removeSubtask(Integer taskId) {
        Subtask subtask = subtasks.get(taskId);
        subtasks.remove(taskId);
        updateStatus(subtask.epicId);
    }

    public ArrayList<Integer> getEpicsSubtasks(Integer number) {
        Epic epic = epics.get(number);
        ArrayList<Integer> subtasks = epic.subtaskIds;
        return subtasks;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    private void updateStatus(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            boolean containsDone = false;
            boolean containsNew = false;
            boolean containsInProgress = false;

            for (Integer subtaskId : epic.subtaskIds) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    if (subtask.statusOfTask.equals("NEW")) {
                        containsNew = true;
                    } else if (subtask.statusOfTask.equals("DONE")) {
                        containsDone = true;
                    } else {
                        containsInProgress = true;
                    }
                }
            }

            if (containsInProgress || containsNew && containsDone) {
                epic.statusOfTask = "IN_PROGRESS";
            } else if (containsNew && !containsDone && !containsInProgress) { // добавил все взаимоисключающие варианты
                epic.statusOfTask = "NEW";
            } else if (containsDone && !containsNew && !containsInProgress) {
                epic.statusOfTask = "DONE";
            } else {
                epic.statusOfTask = "NEW";
            }
        }
    }

    private int getNumberOfTask() {
        numberOfTask++;
        return numberOfTask;
    }
}
