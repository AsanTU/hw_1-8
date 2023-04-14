import HomeFragment.Companion.EDIT_KEY
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw_1_8.databinding.FragmentNewTaskBinding

class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private lateinit var task: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if(arguments!=null){
                updateData(task)
            }else{
                saveData()
            }
            findNavController().navigateUp()
        }
    }

    private fun initViews() {
        if(arguments!=null){
            binding.btnSave.text="Update"
            task= arguments?.getSerializable(EDIT_KEY) as Task
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.desc)
        }else{
            binding.btnSave.text="Save"
        }
    }

    private fun saveData(){
        App.db.taskDao().insert(
            Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            ))

        Log.e("ololo","Заметка создана:"+binding.etTitle.text.toString())
    }

    private fun updateData(taskModel: Task){
        taskModel.title=binding.etTitle.text.toString()
        taskModel.desc=binding.etDesc.text.toString()

        App.db.taskDao().update(taskModel)
    }
}
