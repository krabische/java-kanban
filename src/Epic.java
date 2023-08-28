import java.util.ArrayList;

public class Epic extends Task{
    public Epic(String taskName, String description) {
        super(taskName, description);
    }
public ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic() {

    }
}
