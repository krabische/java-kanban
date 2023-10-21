package tasks;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
    }

    public Subtask(int id, String taskName, String description, Status statusOfTask) {
        super(id, taskName, description, statusOfTask);
    }

    public Subtask(int id, String name, Status status, String description, String epic) {
        super(id, name, status, description, epic);
        this.type = TaskTypes.SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }
}
