package ru.yandex.practicum.java_kanban;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    private ArrayList<Integer> SubtasksId = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description);
    }

    public Epic(String name, String description) {
        super(name, description);
    }

    public void addSubtask(Subtask subtask) {
        SubtasksId.add(subtask.getId());
    }

    public ArrayList<Integer> getSubtasks() {
        return SubtasksId;
    }

    public void setSubtasks(ArrayList<Integer> subtasksId) {
        this.SubtasksId = subtasksId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(SubtasksId, epic.SubtasksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), SubtasksId);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtasks=" + SubtasksId +
                '}';
    }
}
