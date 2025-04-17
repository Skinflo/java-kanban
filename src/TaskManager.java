import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 0;

    HashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    HashMap<Integer, Epic> getAllEpics() {
        return epics;
    }

    HashMap<Integer, Subtask> getAllSubtasks() {
        return subtasks;
    }

    void deleteALlTasks() {
        tasks.clear();
    }

    void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setSubtasks(new ArrayList<>());
        }
    }

    Task getTaskById(int id) {
        return tasks.get(id);
    }

    Epic getEpicById(int id) {
        return epics.get(id);
    }

    Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    void createTask(Task task) {
        tasks.put(task.getId(), task);
        id++;
    }

    void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        id++;
    }

    void createSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        id++;
        epics.get(subtask.getEpicId()).addSubtask(subtask);
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    void updateEpic(Epic epic) {
        epic.setSubtasks(epics.get(epic.getId()).getSubtasks());
        epic.setStatus(epics.get(epic.getId()).getStatus());
        epics.put(epic.getId(), epic);
    }

    void updateSubtask(Subtask subtask) {
        ArrayList<Subtask> epicSubtasks = epics.get(subtask.getEpicId()).getSubtasks();
        epicSubtasks.remove(subtasks.get(subtask.getId()));
        epicSubtasks.add(subtask);
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    void deleteTaskById(int id) {
        tasks.remove(id);
    }

    void deleteEpicById(int id) {
        for (Subtask subtask : epics.get(id).getSubtasks()) {
            if (subtask.getEpicId() == id) {
                subtasks.remove(subtask.getId());
            }
        }
        epics.remove(id);
    }

    void deleteSubtaskById(int id) {
        int epicId = subtasks.get(id).getEpicId();
        epics.get(epicId).getSubtasks().remove(subtasks.get(id));
        subtasks.remove(id);
    }

    ArrayList<Subtask> getSubtasksByEpic(int id) {
        return epics.get(id).getSubtasks();
    }

    private void updateEpicStatus(Epic epic) {
        for (Subtask subtask : epic.getSubtasks()) {
            if (subtask.getStatus() == TaskStatus.DONE) {
                epic.setStatus(TaskStatus.DONE);
            } else {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                return;
            }
        }
    }

    public int getId() {
        return id;
    }
}
