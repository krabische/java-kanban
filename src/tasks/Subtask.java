package tasks;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
    }

    public Subtask(int id, String name, Status status, String description, int epic) {
        super(id, name, status, description, epic);
        this.type = TaskTypes.SUBTASK;
        this.epicId = epic;
    }

    public int getEpicId() {
        return epicId;
    }
}
