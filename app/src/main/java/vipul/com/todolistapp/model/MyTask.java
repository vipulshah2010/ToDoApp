package vipul.com.todolistapp.model;

public class MyTask {
    private String title;
    private String description;

    public MyTask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyTask) {
            MyTask task = (MyTask) obj;
            return this.title.equals(task.title);
        }
        return false;
    }
}
