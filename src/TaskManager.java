import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    int numberOfTask = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Task getTask(Integer insert);

    public Epic getEpic(Integer insert);

    public Subtask getSubtask(Integer insert);

    public void putTask(Task task);

    public void putEpic(Epic epic);

    public void putSubtask(Subtask subtask);

    public void changeEpic(Epic epic);

    public void changeTask(Task task);

    public void changeSubtask(Subtask subtask);

    public void removeTask(Integer taskId);

    public void removeTasks();

    public void removeEpics();

    public void removeSubtasks();

    public void removeEpic(Integer epicId);

    public void removeSubtask(Integer taskId);

    public ArrayList<Task> getAllTasks();

    public ArrayList<Epic> getAllEpics();

    public ArrayList<Subtask> getAllSubtasks();

    public ArrayList<Subtask> getEpicsSubtasks(Integer number);

    void updateStatus(Integer epicId);

    int getNumberOfTask();
}
