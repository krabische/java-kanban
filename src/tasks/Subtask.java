package tasks;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epic) {
        super(taskName, description, epic);
        this.epicId = epic;
        this.type = TaskTypes.SUBTASK;
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
