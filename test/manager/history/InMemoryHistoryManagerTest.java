package manager.history;

import manager.Managers;
import manager.task.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    private InMemoryHistoryManager historyManager;
    private TaskManager taskManager;

    @BeforeEach
    public void BeforeEach() {
        historyManager = (InMemoryHistoryManager) Managers.getDefaultHistory();
        taskManager = Managers.getDefault();
    }

    @Test
    void add_addTaskToHistory() {
        Task task = new Task("1", "1");
        taskManager.createTask(task);

        historyManager.add(task);

        assertNotNull(historyManager.getHistory());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(task, historyManager.getHistory().get(0));
    }

    @Test
    void add_addEpicToHistory() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        historyManager.add(epic);

        assertNotNull(historyManager.getHistory());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(epic, historyManager.getHistory().get(0));
    }

    @Test
    void add_addSubtaskToHistory() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("1", "1", epic.getId());
        taskManager.createSubtask(subtask);

        historyManager.add(subtask);

        assertNotNull(historyManager.getHistory());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(subtask, historyManager.getHistory().get(0));
    }

    @Test
    void add_saveHistory_whenTaskUpdate() {
        Task task = new Task("1", "1");
        taskManager.createTask(task);

        historyManager.add(task);

        task = new Task(task.getId(), "2", "2", TaskStatus.DONE);
        taskManager.updateTask(task);

        assertEquals("1", historyManager.getHistory().get(0).getName());
        assertEquals("1", historyManager.getHistory().get(0).getDescription());
        assertEquals(TaskStatus.NEW, historyManager.getHistory().get(0).getStatus());
    }

    @Test
    void add_saveHistory_whenEpicUpdate() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        historyManager.add(epic);

        epic = new Epic(epic.getId(), "2", "2");
        taskManager.updateEpic(epic);

        assertEquals("1", historyManager.getHistory().get(0).getName());
        assertEquals("1", historyManager.getHistory().get(0).getDescription());
        assertEquals(TaskStatus.NEW, historyManager.getHistory().get(0).getStatus());
    }

    @Test
    void add_saveHistory_whenSubtackUpdate() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("1", "1", epic.getId());
        taskManager.createSubtask(subtask);

        historyManager.add(subtask);

        subtask = new Subtask(subtask.getId(), "2", "2", TaskStatus.DONE, epic.getId());
        taskManager.updateSubtask(subtask);

        assertEquals("1", historyManager.getHistory().get(0).getName());
        assertEquals("1", historyManager.getHistory().get(0).getDescription());
        assertEquals(TaskStatus.NEW, historyManager.getHistory().get(0).getStatus());
    }

    @Test
    void add_deleteFirst_addedMoreThanTen() {
        Task task1 = new Task("1", "1");
        taskManager.createTask(task1);

        Task task2 = new Task("2", "2");
        taskManager.createTask(task2);

        for (int i = 0; i <= 10; i++) {
            if (i == 1) {
                historyManager.add(task2);
            } else {
                historyManager.add(task1);
            }
        }

        assertEquals(10, historyManager.getHistory().size());
        assertEquals(task2, historyManager.getHistory().get(0));
    }
}