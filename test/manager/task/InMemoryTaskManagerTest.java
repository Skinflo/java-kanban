package manager.task;

import manager.Managers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = (InMemoryTaskManager) Managers.getDefault();
    }

    @Test
    public void createTask_addIdAndNotChangeOtherFields() {
        Task task = new Task("1", "1", TaskStatus.NEW);
        taskManager.createTask(task);

        assertNotNull(taskManager.getAllTasks());
        assertNotNull(taskManager.getTaskById(0));
        assertEquals(0, taskManager.getTaskById(0).getId());
        assertEquals("1", taskManager.getTaskById(0).getName());
        assertEquals("1", taskManager.getTaskById(0).getDescription());
        assertEquals(TaskStatus.NEW, taskManager.getTaskById(0).getStatus());
    }

    @Test
    public void createEpic_addIdAndNotChangeOtherFields() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        assertNotNull(taskManager.getAllEpics());
        assertNotNull(taskManager.getEpicById(0));
        assertEquals(0, taskManager.getEpicById(0).getId());
        assertEquals("1", taskManager.getEpicById(0).getName());
        assertEquals("1", taskManager.getEpicById(0).getDescription());
        assertEquals(TaskStatus.NEW, taskManager.getEpicById(0).getStatus());
        assertEquals(0, taskManager.getEpicById(0).getSubtasks().size());
    }

    @Test
    public void createSubtack_addIdAndNotChangeOtherFields() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("2", "2", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask);

        assertNotNull(taskManager.getAllSubtasks());
        assertNotNull(taskManager.getSubtaskById(1));
        assertEquals(1, taskManager.getSubtaskById(1).getId());
        assertEquals("2", taskManager.getSubtaskById(1).getName());
        assertEquals("2", taskManager.getSubtaskById(1).getDescription());
        assertEquals(TaskStatus.NEW, taskManager.getSubtaskById(1).getStatus());
        assertEquals(0, taskManager.getSubtaskById(1).getEpicId());
    }

    @Test
    public void createTask_checkIdConflict() {
        Task task1 = new Task(1, "1", "1", TaskStatus.NEW);
        taskManager.createTask(task1);

        Task task2 = new Task(1, "1", "1", TaskStatus.NEW);
        taskManager.createTask(task2);

        assertNotEquals(task1, task2);
        assertNotEquals(task1.getId(), task2.getId());
    }

    @Test
    public void createSubtack_testSubtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask(138, "2", "2", TaskStatus.NEW, 138);
        taskManager.createSubtask(subtask);

        assertEquals(0, taskManager.getAllSubtasks().size());
    }

    @Test
    public void deleteTask_deleteFromTasksList() {
        Task task = new Task("1", "1", TaskStatus.NEW);
        taskManager.createTask(task);

        taskManager.deleteTaskById(0);

        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    public void deleteEpic_deleteFromEpicsListAndDeleteAllEpicsSubtasks() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("2", "2", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask);

        taskManager.deleteEpicById(0);

        assertTrue(taskManager.getAllEpics().isEmpty());
        assertTrue(taskManager.getAllSubtasks().isEmpty());
    }

    @Test
    public void deleteSubtack_deleteSubtackFromSubtacksListAndDeleteFromEpic() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("2", "2", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask);

        taskManager.deleteSubtaskById(1);

        assertTrue(taskManager.getAllSubtasks().isEmpty());
        assertTrue(epic.getSubtasks().isEmpty());
    }

    @Test
    public void deleteAllTask_deleteAllTackFromTasksList() {
        Task task = new Task("1", "1", TaskStatus.NEW);

        for (int i = 0; i < 5; i++) {
            taskManager.createTask(task);
        }

        taskManager.deleteALlTasks();

        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    public void deleteAllEpic_deleteAllEpicFromEpicsListAndDeleteAllSubtaskFromSubtasksList() {
        Epic epic = new Epic("1", "1");
        Subtask subtask = new Subtask("2", "2", TaskStatus.NEW, epic.getId());

        for (int i = 0; i < 5; i++) {
            taskManager.createEpic(epic);
            taskManager.createSubtask(subtask);
        }

        taskManager.deleteAllEpics();

        assertTrue(taskManager.getAllEpics().isEmpty());
        assertTrue(taskManager.getAllSubtasks().isEmpty());
    }

    @Test
    public void deleteAllSubtack_deleteAllSubtaskFromSubtasksListAndEpic() {
        Epic epic = new Epic("1", "1");
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("2", "2", TaskStatus.NEW, epic.getId());

        for (int i = 0; i < 5; i++) {
            taskManager.createSubtask(subtask);
        }

        taskManager.deleteAllSubtasks();
        assertTrue(taskManager.getAllSubtasks().isEmpty());
        assertTrue(epic.getSubtasks().isEmpty());
    }
}