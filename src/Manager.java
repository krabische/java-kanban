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

  /*  public void changeTaskStatus(Integer number, String newStatus) {
        Task task = tasks.get(number);
        if (task != null) {
            task.setStatusOfTask(newStatus);
        }
    }

    public void changeSubtaskStatus(Integer number, String newStatus) {
        Subtask subtask = subtasks.get(number);
        if (subtask != null) {
            subtask.setStatusOfTask(newStatus);
        }
    } */

    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
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

    public ArrayList<Task> getTasksString() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpicsString() {
        return new ArrayList<>(epics.values());
    }

    private void updateStatus(Integer epicId){
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

            if (containsInProgress) {
                epic.statusOfTask = "IN_PROGRESS";
            } else if (containsNew) {
                epic.statusOfTask = "NEW";
            } else if (containsDone) {
                epic.statusOfTask = "DONE";
            }
        }
    }

 /*   private void checkStatus(Integer epicId) {
        for (int i = (tasks.size() + 1); i <= (epics.size() + tasks.size()); i++) {
            int counter = 0;
            Epic epic = epics.get(i);
            for (int j = 0; j < epic.subtaskIds.size(); j++) {
                Subtask subtask = subtasks.get(epic.subtaskIds.get(j));
                if (subtask.statusOfTask.equals("DONE")) {
                    counter++;
                }
            }
            if (counter > 0 && epic.subtaskIds.size() > counter) {
                epic.statusOfTask = "IN_PROGRESS";
            } else if (counter == epic.subtaskIds.size()) {
                epic.statusOfTask = ("DONE");
            }
        }
    } */

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    private int getNumberOfTask() {
        numberOfTask++;
        return numberOfTask;
    }
}
