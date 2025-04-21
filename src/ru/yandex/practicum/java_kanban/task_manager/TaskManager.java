package ru.yandex.practicum.java_kanban.task_manager;

import ru.yandex.practicum.java_kanban.tasks.Epic;
import ru.yandex.practicum.java_kanban.tasks.Subtask;
import ru.yandex.practicum.java_kanban.tasks.Task;
import ru.yandex.practicum.java_kanban.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 0;

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteALlTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.deleteSubtasks();
            updateEpicStatus(epic);
        }
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return;
        }
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);
        epic.addSubtask(subtask.getId());
        updateEpicStatus(epic);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epic.setSubtasks(epics.get(epic.getId()).getSubtasks());
        epic.setStatus(epics.get(epic.getId()).getStatus());
        epics.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
       subtasks.put(subtask.getId(), subtask);
       updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteEpicById(int id) {
        for (Integer subtaskId : epics.get(id).getSubtasks()) {
            if (subtaskId == id) {
                subtasks.remove(subtaskId);
            }
        }
        epics.remove(id);
    }

    public void deleteSubtaskById(int id) {
        int epicId = subtasks.get(id).getEpicId();
        epics.get(epicId).getSubtasks().remove(subtasks.get(id).getId());
        subtasks.remove(id);
        updateEpicStatus(epics.get(epicId));
    }

    public ArrayList<Subtask> getSubtasksByEpic(int id) {
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
            int doneSubtasks = 0;
            int newSubtasks = 0;
            for (Integer subtaskId : epic.getSubtasks()) {
                if (subtasks.get(subtaskId).getStatus() == TaskStatus.DONE) {
                    doneSubtasks++;
                } else if (subtasks.get(subtaskId).getStatus() == TaskStatus.NEW) {
                    newSubtasks++;
                }
            }
            if (doneSubtasks == epic.getSubtasks().size()) {
                epic.setStatus(TaskStatus.DONE);
            } else if (newSubtasks == epic.getSubtasks().size()) {
                epic.setStatus(TaskStatus.NEW);
            } else {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }

    private int getNextId() {
        return id++;
    }
}
