package allmanagers;

import java.util.ArrayList;
import java.util.List;

import alltasks.*;

public interface TaskManager {

    Task getTask(Integer insert);

    Epic getEpic(Integer insert);

    Subtask getSubtask(Integer insert);

    void putTask(Task task);

    void putEpic(Epic epic);

    void putSubtask(Subtask subtask);

    void changeEpic(Epic epic);

    void changeTask(Task task);

    void changeSubtask(Subtask subtask);

    void removeTask(Integer taskId);

    void removeTasks();

    void removeEpics();

    void removeSubtasks();

    void removeEpic(Integer epicId);

    void removeSubtask(Integer taskId);

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    ArrayList<Subtask> getEpicsSubtasks(Integer number);

    List<Task> getHistory();

}
