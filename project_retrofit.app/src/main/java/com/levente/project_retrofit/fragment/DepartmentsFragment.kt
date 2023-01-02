package com.levente.project_retrofit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levente.project_retrofit.R
import com.levente.project_retrofit.adapter.DepartmentsAdapter
import com.levente.project_retrofit.api.ThreeTrackerRepository
import com.levente.project_retrofit.api.model.SettingsResponse
import com.levente.project_retrofit.viewmodel.*

class DepartmentsFragment : Fragment() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var departmentViewModel: DepartmentViewModel
    private lateinit var profileViewModel: SettingsViewModel
    private lateinit var usersViewModel: UsersViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DepartmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val departmentFactory = DepartmentViewModelFactory(ThreeTrackerRepository())
        val profileFactory = SettingsViewModelFactory(ThreeTrackerRepository())
        val usersFactory = UsersViewModelFactory(ThreeTrackerRepository())

        departmentViewModel = ViewModelProvider(requireActivity(), departmentFactory)[DepartmentViewModel::class.java]
        profileViewModel = ViewModelProvider(requireActivity(), profileFactory)[SettingsViewModel::class.java]
        usersViewModel = ViewModelProvider(requireActivity(), usersFactory)[UsersViewModel::class.java]

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_department_list, container, false)

        recyclerView = view.findViewById(R.id.department_recycler_view)
        setupRecyclerView()

        usersViewModel.users.observe(viewLifecycleOwner) {
            Log.d(TAG, "Users list = $it")

            val myDepartmentId = profileViewModel.setting.value?.departmentId
            val userListByDepartment = myDepartmentId?.let { it1 ->
                usersViewModel.getUsersByDepartmentID(
                    it1
                )
            }

            adapter.setData(userListByDepartment as ArrayList<SettingsResponse>)
            adapter.notifyDataSetChanged()
        }

        return view
    }


    private fun setupRecyclerView() {
        adapter = DepartmentsAdapter(ArrayList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        )
        recyclerView.setHasFixedSize(true)
    }

}