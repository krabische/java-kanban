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

        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.subtaskIdsAdd(subtask.getId());
        }
        updateStatus(subtask.getEpicId());
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
            updateStatus(subtask.getEpicId());
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
            epic.subtaskIdsClear();
            epic.statusOfTask = "NEW";
        }
        subtasks.clear();
    }

    public void removeEpic(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(epicId);
        }
    }

    public void removeSubtask(Integer taskId) {
        Subtask subtask = subtasks.get(taskId);
        subtasks.remove(taskId);
        updateStatus(subtask.getEpicId());
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

    public ArrayList<Subtask> getEpicsSubtasks(Integer number) {
        Epic epic = epics.get(number);
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        ArrayList<Subtask> subtasksList = new ArrayList<>();

        for (Integer id : subtaskIds) {
            Subtask subtask = subtasks.get(id);
            if (subtask != null) {
                subtasksList.add(subtask);
            }
        }
        return subtasksList;
    }


    private void updateStatus(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            boolean containsDone = false;
            boolean containsNew = false;
            boolean containsInProgress = false;

            for (Integer subtaskId : epic.getSubtaskIds()) {
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
