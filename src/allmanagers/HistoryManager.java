package allmanagers;
import alltasks.*;

import java.util.List;

public interface HistoryManager {

    void addCalledTasks(Task task);

    List<Task> getHistory();
}
