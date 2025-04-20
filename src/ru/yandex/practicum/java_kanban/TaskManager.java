package ru.yandex.practicum.java_kanban;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 0;

    protected ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    protected ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    protected ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    protected void deleteALlTasks() {
        tasks.clear();
    }

    protected void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    protected void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setSubtasks(new ArrayList<>());
            updateEpicStatus(epic);
        }
    }

    protected Task getTaskById(int id) {
        return tasks.get(id);
    }

    protected Epic getEpicById(int id) {
        return epics.get(id);
    }

    protected Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    protected void createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
    }

    protected void createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
    }

    protected void createSubtask(Subtask subtask) {
        if (epics.get(subtask.getEpicId()) == null) {
            return;
        }
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtask(subtask);
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    protected void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    protected void updateEpic(Epic epic) {
        epic.setSubtasks(epics.get(epic.getId()).getSubtasks());
        epic.setStatus(epics.get(epic.getId()).getStatus());
        epics.put(epic.getId(), epic);
    }

    protected void updateSubtask(Subtask subtask) {
        ArrayList<Subtask> epicSubtasks = new ArrayList<>();
        for (Integer subtasksId : epics.get(subtask.getEpicId()).getSubtasks()) {
            epicSubtasks.add(subtasks.get(subtasksId));
        }
        epicSubtasks.remove(subtasks.get(subtask.getId()));
        epicSubtasks.add(subtask);
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    protected void deleteTaskById(int id) {
        tasks.remove(id);
    }

    protected void deleteEpicById(int id) {
        for (Integer subtaskId : epics.get(id).getSubtasks()) {
            if (subtaskId == id) {
                subtasks.remove(subtaskId);
            }
        }
        epics.remove(id);
    }

    protected void deleteSubtaskById(int id) {
        int epicId = subtasks.get(id).getEpicId();
        epics.get(epicId).getSubtasks().remove(subtasks.get(id).getId());
        subtasks.remove(id);
        updateEpicStatus(epics.get(epicId));
    }

    protected ArrayList<Subtask> getSubtasksByEpic(int id) {
        ArrayList<Subtask> subtasksByEpic = new ArrayList<>();
        for (Integer subtaskId : epics.get(id).getSubtasks()) {
            subtasksByEpic.add(subtasks.get(subtaskId));
        }
        return subtasksByEpic;
    }

    private void updateEpicStatus(Epic epic) {
        if (epic.getSubtasks().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            for (Integer subtaskId : epic.getSubtasks()) {
                if (subtasks.get(subtaskId).getStatus() == TaskStatus.DONE) {
                    epic.setStatus(TaskStatus.DONE);
                } else {
                    epic.setStatus(TaskStatus.IN_PROGRESS);
                    return;
                }
            }
        }
    }

    private int getNextId() {
        return id++;
    }
}
