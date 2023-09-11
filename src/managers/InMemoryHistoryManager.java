package allmanagers;

import alltasks.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager { //работает с хистори задач


    private final List<Task> calledTasks = new ArrayList<>();


    @Override
    public void addCalledTasks(Task task) {
        calledTasks.add(task);
        if (calledTasks.size() >= 10) {
            calledTasks.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return calledTasks;
    }
}
