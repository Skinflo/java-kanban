package task;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    private ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description);
    }

    public Epic(String name, String description) {
        super(name, description);
    }

    public void addSubtask(Integer id) {
        subtasksId.add(id);
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasksId;
    }

    public void deleteSubtasks() {
        subtasksId.clear();
    }

    public void setSubtasks(ArrayList<Integer> subtasksId) {
        this.subtasksId = subtasksId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasksId);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtasks=" + subtasksId +
                '}';
    }
}
