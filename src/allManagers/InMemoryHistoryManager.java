package allManagers;

import allTasks.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager { //работает с хистори задач


    private static final List<Task> calledTasks = new ArrayList<>();

    @Override
    public List<Task> getCalledTasks() {
        return calledTasks;
    }

    @Override
    public void setCalledTasks(Task task) {
        if (calledTasks.size() <= 10) {
            calledTasks.add(task);
        } else {
            calledTasks.remove(0);
            calledTasks.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getCalledTasks();
    }
}
