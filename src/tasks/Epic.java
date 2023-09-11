package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String taskName, String description) {
        super(taskName, description);
    }

    public Epic(int id, String taskName, String description, Status statusOfTask) {
        super(id, taskName, description, statusOfTask);
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void subtaskIdsClear(){
        subtaskIds.clear();
    }

    public void subtaskIdsAdd (Integer id) {
        subtaskIds.add(id);
    }
}
