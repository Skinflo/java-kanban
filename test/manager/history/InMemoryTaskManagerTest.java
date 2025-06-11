package manager.history;

import manager.task.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void createTask_addTaskToTasks() {
        Task task1 = new Task("1", "1");
        taskManager.createTask(task1);

        assertNotEquals(0, taskManager.getAllTasks().size());
        assertEquals(task1, taskManager.getTaskById(task1.getId()));
    }

    @Test
    public void createEpic_addEpicToEpics() {
        Epic epic1 = new Epic("1", "1");
        taskManager.createEpic(epic1);

        assertNotEquals(0, taskManager.getAllEpics().size());
        assertEquals(epic1, taskManager.getEpicById(epic1.getId()));
    }

    @Test
    public void createSubtask_addSubtaskToSubtasksAndEpic() {
        Epic epic1 = new Epic("1", "1");
        taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("1", "1", epic1.getId());
        taskManager.createSubtask(subtask1);

        assertNotEquals(0, taskManager.getAllSubtasks().size());
        assertEquals(subtask1, taskManager.getSubtaskById(subtask1.getId()));
        assertEquals(1,epic1.getSubtasks().size());
    }

    //Удаляемые подзадачи не должны хранить внутри себя старые id.
    @Test
    public void deleteSubtaskById_deleteSubtaskShouldNotStoreOldId() {
        Epic epic1 = new Epic("1", "1");
        taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("1", "1", epic1.getId());
        taskManager.createSubtask(subtask1);

        taskManager.deleteSubtaskById(subtask1.getId());

        taskManager.createSubtask(subtask1);

        assertNotEquals(1, subtask1.getId());
        assertEquals(2, subtask1.getId());
    }

    //Внутри эпиков не должно оставаться неактуальных id подзадач.
    @Test
    public void deleteSubtaskById_deleteSubtackIdFromEpic() {
        Epic epic1 = new Epic("1", "1");
        taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("1", "1", epic1.getId());
        taskManager.createSubtask(subtask1);

        taskManager.deleteSubtaskById(subtask1.getId());

        assertTrue(epic1.getSubtasks().isEmpty());
        assertEquals(0, epic1.getSubtasks().size());
    }

    //Внутри эпиков не должно оставаться неактуальных id подзадач.
    @Test
    public void deleteAllSubtasks_deleteSubtacksIdFromEpic() {
        Epic epic1 = new Epic("1", "1");
        taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("1", "1", epic1.getId());
        taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("1", "1", epic1.getId());
        taskManager.createSubtask(subtask2);

        taskManager.deleteAllSubtasks();

        assertTrue(epic1.getSubtasks().isEmpty());
        assertEquals(0, epic1.getSubtasks().size());
    }
}
