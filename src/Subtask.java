public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
    }

    public Subtask(int id, String taskName, String description, String statusOfTask) {
        super(id, taskName, description, statusOfTask);
    }

    public int getEpicId() {
        return epicId;
    }
}
