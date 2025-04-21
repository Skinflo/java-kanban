package manager;

import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    // Тесты брал из ТЗ, за исключением этих, так как логика приложения не дает создавать эпик как сабтаск
    // и сабтаск как эпик.
    // 1. проверьте что объект Epic нельзя добавить в самого себя в виде подзадачи;
    // 2. проверьте, что объект Subtask нельзя сделать своим же эпиком;

    TaskManager taskManager = Managers.getDefault();

    @Test
    public void shouldReturnTrueWhenTasksHaveSameId() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        Task task2 = task1;
        assertEquals(task1.getId(), task2.getId(), "Задачи не равны");
    }

    @Test
    public void shouldReturnTrueWhenEpicsHaveSameId() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        Epic epic2 = epic1;
        assertEquals(epic1.getId(), epic2.getId(), "Эпики не равны");
    }

    @Test
    public void shouldReturnTrueWhenSubtasksHaveSameId() {
        Subtask subtask1 = new Subtask("subtask1", "Описание", 0);
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = subtask1;
        assertEquals(subtask1.getId(), subtask2.getId(), "Сабтаски не равны");
    }

    @Test
    public void shouldReturnTrueWhenGetTaskByIdReturnNotNull() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        assertNotNull(taskManager.getTaskById(task1.getId()), "Возвращает null");
    }

    @Test
    public void shouldReturnTrueWhenGetEpicByIdReturnNotNull() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        assertNotNull(taskManager.getEpicById(epic1.getId()), "Возвращает null");
    }

    @Test
    public void shouldReturnTrueWhenGetSubtaskByIdReturnNotNull() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("subtask1", "Описание", epic1.getId());
        taskManager.createSubtask(subtask1);
        assertNotNull(taskManager.getSubtaskById(subtask1.getId()), "Возвращает null");
    }

    @Test
    public void shouldReturnTrueWhenTasksHaveDifferentId() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        Task task2 = new Task(0, "task2", "Описание");
        taskManager.createTask(task2);
        assertNotEquals(task1.getId(), task2.getId(), "id разных задач конфликтуют");
    }

    @Test
    public void shouldReturnTrueWhenTaskAndCreateTaskHaveSameValues() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        assertEquals(task1.getId(), taskManager.getTaskById(task1.getId()).getId(),
                "Id отличаются");
        assertEquals(task1.getName(), taskManager.getTaskById(task1.getId()).getName(),
                "Имена отличаются");
        assertEquals(task1.getDescription(), taskManager.getTaskById(task1.getId()).getDescription(),
                "Описание отличаются");
        assertEquals(task1.getStatus(), taskManager.getTaskById(task1.getId()).getStatus(),
                "Статусы отличаются");
    }

    @Test
    public void shouldReturnTrueWhenHistorySaveTask() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        taskManager.getTaskById(task1.getId());
        assertNotNull(taskManager.getHistory().get(0), "Задача не добавляется в историю");
    }

    @Test
    public void shouldReturnNullWhenTaskDeleted() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        taskManager.deleteTaskById(task1.getId());
        assertNull(taskManager.getTaskById(task1.getId()), "Задача не удалена");
    }

    @Test
    public void shouldReturnNullWhenAllTasksDeleted() {
        Task task1 = new Task("task1", "Описание");
        taskManager.createTask(task1);
        Task task2 = new Task("task2", "Описание");
        taskManager.createTask(task2);
        taskManager.deleteALlTasks();
        assertTrue(taskManager.getAllTasks().isEmpty(), "Задачи не удалены");
    }

    @Test
    public void shouldReturnNullWhenEpicDeleted() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        taskManager.deleteEpicById(epic1.getId());
        assertNull(taskManager.getEpicById(epic1.getId()), "Эпик не удален");
    }

    @Test
    public void shouldReturnNullWhenAllEpicsDeleted() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        Epic epic2 = new Epic("epic2", "Описание");
        taskManager.createEpic(epic2);
        taskManager.deleteAllEpics();
        assertTrue(taskManager.getAllEpics().isEmpty(), "Эпики не удалены");
    }

    @Test
    public void shouldReturnNullWhenSubtaskDeleted() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("subtask1", "Описание", epic1.getId());
        taskManager.createSubtask(subtask1);
        taskManager.deleteSubtaskById(subtask1.getId());
        assertNull(taskManager.getSubtaskById(subtask1.getId()), "Сабтаск не удален");
    }

    @Test
    public void shouldReturnNullWhenAllSubtasksDeleted() {
        Epic epic1 = new Epic("epic1", "Описание");
        taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("subtask1", "Описание", epic1.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("subtask2", "Описание", epic1.getId());
        taskManager.createSubtask(subtask1);
        taskManager.deleteAllSubtasks();
        assertTrue(taskManager.getAllSubtasks().isEmpty(), "Сабтаски не удалены");
    }
}
