package manager.history;

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
    void remove_removeTaskFromHistory() {
        Task task = new Task(1, "1", "1");

        historyManager.add(task);
        historyManager.remove(task.getId());

        assertTrue(historyManager.getHistory().isEmpty());
        assertEquals(0, historyManager.getHistory().size());
    }

    @Test
    void remove_removeEpicFromHistory() {
        Epic epic = new Epic(1, "1", "1");

        historyManager.add(epic);
        historyManager.remove(epic.getId());

        assertTrue(historyManager.getHistory().isEmpty());
        assertEquals(0, historyManager.getHistory().size());
    }

    @Test
    void remove_removeSubtaskFromHistory() {
        Subtask subtask = new Subtask(1, "1", "1", TaskStatus.NEW, 2);

        historyManager.add(subtask);
        historyManager.remove(subtask.getId());

        assertTrue(historyManager.getHistory().isEmpty());
        assertEquals(0, historyManager.getHistory().size());
    }


}