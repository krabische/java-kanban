package managers;

import tasks.*;

import java.util.List;

public interface HistoryManager {

    void add(Task task);

    void remove(int id);

    void update(int id, Task task);

    List<Task> getHistory();
}
