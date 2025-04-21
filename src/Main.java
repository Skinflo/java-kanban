import manager.Managers;
import manager.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

//        Создание всех задач + вывод

        Task task1 = new Task( "Первая задача", "Описание");
        taskManager.createTask(task1);

        Task task2 = new Task( "Вторая задача", "Описание");
        taskManager.createTask(task2);

        Epic epic1 = new Epic( "Первый Эпик", "Описание");
        taskManager.createEpic(epic1);

        Epic epic2 = new Epic( "Второй Эпик", "Описание");
        taskManager.createEpic(epic2);

        Subtask subtask1 = new Subtask("Первый сабтаск", "Описание", epic1.getId());
        taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask( "Второй сабтаск", "Описание", epic1.getId());
        taskManager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Третий сабтаск", "Описание", epic2.getId());
        taskManager.createSubtask(subtask3);

        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

//        Обновление задач + вывод

        task1 = new Task(task1.getId(), "Обновленная первая задача", "Обновленное описание",
                TaskStatus.DONE);
        taskManager.updateTask(task1);

        subtask1 = new Subtask(subtask1.getId(), "Обновленный первый сабтаск", "Обновленное описание",
                TaskStatus.IN_PROGRESS, subtask1.getEpicId());
        taskManager.updateSubtask(subtask1);

        subtask2 = new Subtask(subtask2.getId(), "Обновленный второй сабтаск", "Обновленное описание",
                TaskStatus.DONE, epic1.getId());
        taskManager.updateSubtask(subtask2);

        epic1 = new Epic(epic1.getId(), "Обновленный первый эпик", "Обновленное описание");
        taskManager.updateEpic(epic1);

        System.out.println(taskManager.getTaskById(task2.getId()));
        System.out.println(taskManager.getEpicById(epic1.getId()));
        System.out.println(taskManager.getSubtaskById(subtask1.getId()));
        System.out.println(taskManager.getSubtaskById(subtask2.getId()));

        subtask1 = new Subtask(subtask1.getId(), "Обновленный первый сабтаск", "Обновленное описание",
                TaskStatus.DONE, subtask1.getEpicId());
        taskManager.updateSubtask(subtask1);

        System.out.println(taskManager.getEpicById(epic1.getId()));
        System.out.println(taskManager.getSubtasksByEpic(epic2.getId()));

//        Удаление задач + вывод

        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteSubtaskById(subtask1.getId());

        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

        taskManager.deleteSubtaskById(subtask2.getId());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

        taskManager.deleteEpicById(epic1.getId());

        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

//        Удаление всех задач

        taskManager.deleteALlTasks();
        taskManager.deleteAllSubtasks();
        taskManager.deleteAllEpics();

        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

        System.out.println(taskManager.getHistory());
    }
}
