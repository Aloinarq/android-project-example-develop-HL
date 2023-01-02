package com.levente.project_retrofit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.levente.project_retrofit.R
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.databinding.FragmentGroupsBinding
import com.levente.project_retrofit.viewmodel.*


class GroupsFragment : Fragment() {

    companion object{
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var departmentViewModel: DepartmentViewModel

    private lateinit var showMembersButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = SettingsViewModelFactory(ThreeTrackerRepository())

        settingsViewModel = ViewModelProvider(requireActivity(), factory)[SettingsViewModel::class.java]
        departmentViewModel = ViewModelProvider(requireActivity(), DepartmentViewModelFactory(
            ThreeTrackerRepository()
        )
        )[DepartmentViewModel::class.java]
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_groups, container, false)

        val myDepartmentName: TextView = view.findViewById(R.id.myGroupName)

        departmentViewModel.departments.observe(viewLifecycleOwner){
            Log.d(TAG, "Departmtn = $it")

            val myDepartmentId = settingsViewModel.setting.value?.departmentId
            myDepartmentName.text = myDepartmentId?.let { it1 ->
                departmentViewModel.getDepartmentName(
                    it1
                )
            }
        }

        showMembersButton = view.findViewById(R.id.showGroupMembersButton)
        showMembersButton.setOnClickListener { findNavController().navigate(R.id.departmentsFragment) }

        return view
    }
}