package manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private TaskManager taskManager;
    Task task;
    Epic epic;
    Subtask subtask;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();

        task = new Task("1", "1");
        taskManager.createTask(task);

        epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        subtask = new Subtask("1", "1", epic.getId());
        taskManager.createSubtask(subtask);
    }

    @Test
    public void addTask() {
        assertNotNull(taskManager.getAllTasks());
        assertNotNull(taskManager.getTaskById(task.getId()));
    }

    @Test
    public void addEpic() {
        assertNotNull(taskManager.getAllEpics());
        assertNotNull(taskManager.getEpicById(epic.getId()));
    }

    @Test
    public void addSubtack() {
        assertNotNull(taskManager.getAllSubtasks());
        assertNotNull(taskManager.getSubtaskById(subtask.getId()));
    }

    @Test
    public void checkIdConflict() {
        Task task2 = new Task(1, "1", "1");
        taskManager.createTask(task2);
        assertNotEquals(task, task2);
    }

    @Test
    public void taskAndCreateTaskHaveSameValue() {
        assertEquals(0, task.getId());
        assertEquals("1", task.getName());
        assertEquals("1", task.getDescription());
        assertEquals(TaskStatus.NEW, task.getStatus());
    }

    @Test
    public void testSubtaskCannotBeItsOwnEpic() {
        Subtask subtask2 = new Subtask(138, "2", "2", TaskStatus.NEW, 138);
        int subtaskSize = taskManager.getAllSubtasks().size();
        taskManager.createSubtask(subtask2);
        assertEquals(subtaskSize, taskManager.getAllSubtasks().size());
    }

    @Test
    public void deleteTask() {
        taskManager.deleteTaskById(task.getId());
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    public void deleteEpic() {
        taskManager.deleteEpicById(epic.getId());
        assertTrue(taskManager.getAllEpics().isEmpty());
    }

    @Test
    public void deleteSubtack() {
        taskManager.deleteSubtaskById(subtask.getId());
        assertTrue(taskManager.getAllSubtasks().isEmpty());
    }

    @Test
    public void deleteAllTask() {
        taskManager.deleteALlTasks();
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    public void deleteAllEpic() {
        taskManager.deleteAllEpics();
        assertTrue(taskManager.getAllEpics().isEmpty());
    }

    @Test
    public void deleteAllSubtack() {
        taskManager.deleteAllSubtasks();
        assertTrue(taskManager.getAllSubtasks().isEmpty());
    }
}