package managers;
import tasks.*;

import java.util.List;

public interface HistoryManager {

    void addCalledTasks(Task task);

    List<Task> getHistory();
}
