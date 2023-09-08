import java.util.List;

public interface HistoryManager {
    List<Task> getCalledTasks();

    void setCalledTasks(Task task);

    List<Task> getHistory();
}
