import java.util.List;

public class TaskConflictObserver implements TaskObserver {

    private List<Task> tasks;

    public TaskConflictObserver(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }

    public boolean hasConflict(Task newTask) {
        for (Task task : tasks) {
            if (timeOverlap(task, newTask)) {
                notify("Error: Task conflicts with existing task \"" + task.getDescription() + "\".");
                return true;
            }
        }
        return false;
    }

    private boolean timeOverlap(Task task1, Task task2) {
        return !(task1.getEndTime().compareTo(task2.getStartTime()) <= 0 ||
                 task1.getStartTime().compareTo(task2.getEndTime()) >= 0);
    }
}

