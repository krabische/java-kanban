import java.util.HashMap;

public class Manager {
    private int numberOfTask = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Manager() {
    }

    public int getNumberOfTask() {
        numberOfTask++;
        return numberOfTask;
    }

    public void putTask(Task task) {
        task.id = getNumberOfTask();
        tasks.put(task.id, task);
    }

    public void putEpic(Epic epic) {
        epic.id = getNumberOfTask();
        epics.put(epic.id, epic);
    }

    public void putSubtask(Integer number, Subtask subtask) {
        subtask.id = getNumberOfTask();
        subtasks.put(subtask.id, subtask);

        Epic epic = epics.get(number);
        if (epic != null) {
            epic.subtaskIds.add(subtask.id);
        }
    }

    public void changeTask(Integer number, Task task) {
        tasks.put(number, task);
    }

    public void changeEpic(Integer number, Epic task) {
        tasks.put(number, task);
    }

    public void changeSubtask(Integer number, Subtask task) {
        tasks.put(number, task);
    }

    public void changeTaskStatus(Integer number, String newStatus) {
        Task task = tasks.get(number);
        if (task != null) {
            task.setStatusOfTask(newStatus);
        }
    }

    public void changeSubtaskStatus(Integer number, String newStatus) {
        Subtask subtask = subtasks.get(number);
        if (subtask != null) {
            subtask.setStatusOfSubtask(newStatus);
        }
    }

    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
    }

    public void removeEpic(Integer taskId) {
        epics.remove(taskId);
    }

    public void removeSubtask(Integer taskId) {
        subtasks.remove(taskId);
        checkStatus();
    }

    public void printHashTask() {
        for (Task task : tasks.values()) {
            System.out.println("№" + task.id + " - " + task);
        }
    }

    public void printHashEpic() {
        for (Epic epic : epics.values()) {
            System.out.println("№" + epic.id + " - " + epic);
            System.out.println("    Подзадачи:");
            for (Integer subtaskId : epic.subtaskIds) {
                Subtask subtask = subtasks.get(subtaskId);
                System.out.println("    №" + subtaskId + " - " + subtask);
            }
        }
    }

    public void checkStatus() {
        for (int i = (tasks.size() + 1); i <= (epics.size() + tasks.size()); i++) {
            int counter = 0;
            Epic epic = epics.get(i);
            for (int j = 0; j < epic.subtaskIds.size(); j++) {
                Subtask subtask = subtasks.get(epic.subtaskIds.get(j));
                if (subtask.statusOfTask.equals("DONE ✅")) {
                    counter++;
                }
            }
            if (counter > 0 && epic.subtaskIds.size() > counter) {
                epic.statusOfTask = "IN_PROGRESS\uD83D\uDFE1";
            } else if (counter == epic.subtaskIds.size()) {
                epic.statusOfTask = ("DONE ✅");
            }
        }
    }

}
