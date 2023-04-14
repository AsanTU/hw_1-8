import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_1_8.R
import com.example.hw_1_8.databinding.TaskItemBinding

class TaskAdapter(
    private var onClick: (Int) -> Unit,
    private var onLongClick: (Int) -> Unit

) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var taskList= arrayListOf<Task>()

    fun addTask(taskModel: Task){
        taskList.add(0,taskModel)
        notifyDataSetChanged()
    }

    fun addAllTask(list: List<Task>){
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    fun getTask(position: Int):Task{
        return taskList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])

        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.white)
        } else {
            holder.itemView.setBackgroundResource(R.color.gray)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(private var binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: Task) {
            binding.itemTitle.text= taskModel.title
            binding.itemDesc.text = taskModel.desc

            itemView.setOnClickListener {
                // Логика на изменение заметки
                onClick(adapterPosition)
            }

            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
                return@setOnLongClickListener true

            }
        }
    }
}

