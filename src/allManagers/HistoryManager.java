package allManagers;
import allTasks.*;

import java.util.List;

public interface HistoryManager {
    List<Task> getCalledTasks();

    void addCalledTasks(Task task);

    List<Task> getHistory();
}
