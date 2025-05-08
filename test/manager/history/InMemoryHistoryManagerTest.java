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

    @BeforeEach
    public void beforeEach() {
        historyManager = new InMemoryHistoryManager();

    }

    @Test
    void add_addTaskToHistory() {
        Task task = new Task("1", "1");

        historyManager.add(task);

        assertNotNull(historyManager.getHistory());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(task, historyManager.getHistory().get(0));
    }

    @Test
    void add_addEpicToHistory() {
        Epic epic = new Epic("1", "1");

        historyManager.add(epic);

        assertNotNull(historyManager.getHistory());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(epic, historyManager.getHistory().get(0));
    }

    @Test
    void add_addSubtaskToHistory() {
        Subtask subtask = new Subtask("1", "1", 0);

        historyManager.add(subtask);

        assertNotNull(historyManager.getHistory());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(subtask, historyManager.getHistory().get(0));
    }

    @Test
    void add_saveHistory_whenTaskUpdate() {
        Task task = new Task("1", "1");

        historyManager.add(task);

        task.setName("2");
        task.setDescription("2");
        task.setId(2);
        task.setStatus(TaskStatus.DONE);

        assertEquals("1", historyManager.getHistory().get(0).getName());
        assertEquals("1", historyManager.getHistory().get(0).getDescription());
        assertEquals(TaskStatus.NEW, historyManager.getHistory().get(0).getStatus());
    }

    @Test
    void add_saveHistory_whenEpicUpdate() {
        Epic epic = new Epic("1", "1");

        historyManager.add(epic);

        epic.setId(2);
        epic.setName("2");
        epic.setDescription("2");
        epic.setStatus(TaskStatus.DONE);

        assertEquals("1", historyManager.getHistory().get(0).getName());
        assertEquals("1", historyManager.getHistory().get(0).getDescription());
    }

    @Test
    void add_saveHistory_whenSubtackUpdate() {
        Subtask subtask = new Subtask("1", "1", 0);

        historyManager.add(subtask);

        subtask.setId(2);
        subtask.setName("2");
        subtask.setDescription("2");
        subtask.setStatus(TaskStatus.DONE);

        assertEquals("1", historyManager.getHistory().get(0).getName());
        assertEquals("1", historyManager.getHistory().get(0).getDescription());
        assertEquals(TaskStatus.NEW, historyManager.getHistory().get(0).getStatus());
    }

    @Test
    void add_deleteFirst_addedMoreThanTen() {
        Task task1 = new Task("1", "1");

        Task task2 = new Task("2", "2");

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