import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String taskName, String description) {
        super(taskName, description);
    }

    public Epic(int id, String taskName, String description, String statusOfTask) {
        super(id, taskName, description, statusOfTask);
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }
}
