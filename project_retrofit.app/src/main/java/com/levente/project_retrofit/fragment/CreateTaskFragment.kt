package com.levente.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.levente.project_retrofit.App
import com.levente.project_retrofit.R
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.manager.SharedPreferencesManager
import com.levente.project_retrofit.viewmodel.CreateTaskViewModel
import com.levente.project_retrofit.viewmodel.CreateTaskViewModelFactory
import com.levente.project_retrofit.viewmodel.LoginViewModel
import com.levente.project_retrofit.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.my_actionbar.*

class CreateTaskFragment : Fragment(){
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var createTaskViewModel: CreateTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CreateTaskViewModelFactory(ThreeTrackerRepository())
        createTaskViewModel = ViewModelProvider(this, factory)[CreateTaskViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_task, container, false)

        val titleEditText: EditText = view.findViewById(R.id.taskName)
        val descriptionEditText: EditText = view.findViewById(R.id.taskDescription)
        val button: Button = view.findViewById(R.id.createTaskButton)

        Log.d(
            TAG,
            "token = " + App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
        )

        button.setOnClickListener {

            createTaskViewModel.createTask(titleEditText.text.toString(), descriptionEditText.text.toString(), 68, 1, 1625942327, 2, 1)

            createTaskViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "Task created successfully = $it")
                if (it) {
                    findNavController().navigate(R.id.listFragment)
                }
            }
        }

        return view
    }
}