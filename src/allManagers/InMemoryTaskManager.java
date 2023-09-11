package allManagers;

import allTasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private int numberOfTask = 0;
    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();


    @Override
    public Task getTask(Integer insert) {
        Managers.getDefaultHistory().addCalledTasks(tasks.get(insert));
        return tasks.get(insert);
    }

    @Override
    public Epic getEpic(Integer insert) {
        Managers.getDefaultHistory().addCalledTasks(epics.get(insert));
        return epics.get(insert);
    }

    @Override
    public Subtask getSubtask(Integer insert) {
        Managers.getDefaultHistory().addCalledTasks(subtasks.get(insert));
        return subtasks.get(insert);
    }


    @Override
    public void putTask(Task task) {
        task.setId(getNumberOfTask());
        tasks.put(task.getId(), task);
    }

    @Override
    public void putEpic(Epic epic) {
        epic.setId(getNumberOfTask());
        epics.put(epic.getId(), epic);
    }

    @Override
    public void putSubtask(Subtask subtask) {
        subtask.setId(getNumberOfTask());
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.subtaskIdsAdd(subtask.getId());
        }
        updateStatus(subtask.getEpicId());
    }

    @Override
    public void changeEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        }
    }

    @Override
    public void changeTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void changeSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            updateStatus(subtask.getEpicId());
        }
    }

    @Override
    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
    }

    @Override
    public void removeTasks() {
        tasks.clear();
    }

    @Override
    public void removeEpics() {
        removeSubtasks();
        epics.clear();
    }

    @Override
    public void removeSubtasks() {
        for (Epic epic : epics.values()) {
            epic.subtaskIdsClear();
            epic.setStatusOfTask(Status.NEW);
        }
        subtasks.clear();
    }

    @Override
    public void removeEpic(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(epicId);
        }
    }

    @Override
    public void removeSubtask(Integer taskId) {
        Subtask subtask = subtasks.get(taskId);
        subtasks.remove(taskId);
        updateStatus(subtask.getEpicId());
    }


    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
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

    @Override
    public List<Task> getHistory() {
        return Managers.getDefaultHistory().getHistory();
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
                    if (subtask.getStatusOfTask().equals(Status.NEW)) {
                        containsNew = true;
                    } else if (subtask.getStatusOfTask().equals(Status.DONE)) {
                        containsDone = true;
                    } else {
                        containsInProgress = true;
                    }
                }
            }

            if (containsInProgress || containsNew && containsDone) {
                epic.setStatusOfTask(Status.IN_PROGRESS);
            } else if (containsNew && !containsDone && !containsInProgress) { // добавил все взаимоисключающие варианты
                epic.setStatusOfTask(Status.NEW);
            } else if (containsDone && !containsNew && !containsInProgress) {
                epic.setStatusOfTask(Status.DONE);
            } else {
                epic.setStatusOfTask(Status.NEW);
            }
        }
    }


    private int getNumberOfTask() {
        numberOfTask++;
        return numberOfTask;
    }


}
