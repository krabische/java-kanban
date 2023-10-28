package managers;

import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {


    private int numberOfTask = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    HistoryManager historyManager = new InMemoryHistoryManager();

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;

    }

    public InMemoryTaskManager() {
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }


    @Override
    public Task getTask(Integer id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(Integer id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtask(Integer id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }
    @Override
    public void addTask(Task task) {
        if(task.getId() != 0){
            tasks.put(task.getId(), task);
        } else {
            task.setId(getNumberOfTask());
            tasks.put(task.getId(), task);
        }
    }
    @Override
    public void addEpic(Epic epic) {
        if (epic.getId() != 0){
            epics.put(epic.getId(), epic);
        } else {
            epic.setId(getNumberOfTask());
            epics.put(epic.getId(), epic);
        }
    }
    @Override
    public void addSubtask(Subtask subtask) {
        if (subtask.getId() != 0){
            subtasks.put(subtask.getId(), subtask);
        } else {
            subtask.setId(getNumberOfTask());
            subtasks.put(subtask.getId(), subtask);
        }
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
        historyManager.remove(taskId);
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
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    subtasks.remove(subtaskId);
                    historyManager.remove(subtaskId);
                }
            }
            epics.remove(epicId);
            historyManager.remove(epicId);
        }
    }

    @Override
    public void removeSubtask(Integer taskId) {
        Subtask subtask = subtasks.get(taskId);
        if (subtask != null) {
            int epicId = subtask.getEpicId();
            Epic epic = epics.get(epicId);
            if (epic != null) {
                epic.getSubtaskIds().remove(Integer.valueOf(taskId));
                updateStatus(epicId);
            }
            subtasks.remove(taskId);
            historyManager.remove(taskId);
        }
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
        return historyManager.getHistory();
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
