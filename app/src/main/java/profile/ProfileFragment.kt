import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.hw_1_8.databinding.FragmentProfileBinding
import java.util.prefs.Preferences


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private var mGetContent = registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri -> Log.e("ololo",":+ $uri" )

        binding.imageIcon.loadImage(uri.toString())
        Preferences(requireContext()).imgUri = uri.toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initListeners()
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.imageIcon.loadImage(Preferences(requireContext()).imgUri)

        binding.etProfile.setText(Preferences(requireContext()).saveToEditText)

    }

    private fun initListeners() {
        binding.imageIcon.setOnClickListener{
            pickImageFromGallery()
        }
        binding.etProfile.addTextChangedListener(object :TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                Preferences(requireContext()).saveToEditText = s.toString()
            }
        })
    }

    private fun pickImageFromGallery() {
        mGetContent.launch("image/*")
    }
}

// I've commented old version.It also works exactly the same.
// I rewrote it because the function  startActivityForResult is deprecated

/*val intent = Intent(Intent.ACTION_PICK)
     intent.type = "image/*"
     startActivityForResult(intent, IMAGE_PICK_CODE)
        companion object {
        private val IMAGE_PICK_CODE = 1000

       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
               binding.imageIcon.setImageURI(data?.data)
            }
    }*/