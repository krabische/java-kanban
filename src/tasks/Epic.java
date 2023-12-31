package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String taskName, String description) {
        super(taskName, description);
        this.type = TaskTypes.EPIC;
    }


    public Epic(int id, String name, Status status, String description, int epic) {
        super(id, name, status, description, epic);
        this.type = TaskTypes.EPIC;
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
