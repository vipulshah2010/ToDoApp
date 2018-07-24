package vipul.com.todolistapp.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vipul.com.todolistapp.R;
import vipul.com.todolistapp.model.MyTask;

class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskRowHolder> {

    private List<MyTask> tasks;

    private OnTaskSelectedListener listener;

    TaskListAdapter(List<MyTask> tasks) {
        this.tasks = tasks;
    }

    public void setListener(OnTaskSelectedListener listener) {
        this.listener = listener;
    }

    // It defines how many rows will be there!
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Define which layout to be use in row. Helps in constructing a row
    @Override
    public TaskRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_task, parent, false);
        return new TaskRowHolder(view);
    }

    // Define what contents to be shown in each row
    @Override
    public void onBindViewHolder(@NonNull final TaskRowHolder holder, int position) {
        MyTask myTask = tasks.get(position);
        holder.titleTextView.setText(myTask.getTitle());
        holder.descTextView.setText(myTask.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onTaskSelected(tasks.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    public void setTasks(List<MyTask> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    interface OnTaskSelectedListener {
        void onTaskSelected(MyTask task);
    }

    class TaskRowHolder extends RecyclerView.ViewHolder {

        final TextView titleTextView;
        final TextView descTextView;

        TaskRowHolder(@NonNull View view) {
            super(view);

            titleTextView = view.findViewById(R.id.titleTextView);
            descTextView = view.findViewById(R.id.descriptionTextView);
        }
    }
}
