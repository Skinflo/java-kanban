import manager.Managers;
import manager.task.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Первый таск", "Описание");
        taskManager.createTask(task1);

        Task task2 = new Task("Второй таск", "Описание");
        taskManager.createTask(task2);

        Epic epic1 = new Epic("Первый Эпик", "Описание");
        taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Первый сабтаск", "Описание", epic1.getId());
        taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Второй сабтаск", "Описание", epic1.getId());
        taskManager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Третий сабтаск", "Описание", epic1.getId());
        taskManager.createSubtask(subtask3);

        Epic epic2 = new Epic("Второй Эпик", "Описание");
        taskManager.createEpic(epic2);

        taskManager.getEpicById(epic2.getId());
        taskManager.getEpicById(epic2.getId());
        taskManager.getEpicById(epic1.getId());
        taskManager.getEpicById(epic2.getId());
        taskManager.getSubtaskById(subtask2.getId());
        taskManager.getTaskById(task1.getId());

        System.out.println(taskManager.getHistory());

        taskManager.deleteEpicById(epic1.getId());
        taskManager.deleteTaskById(task1.getId());
        System.out.println(taskManager.getHistory());
    }
}
