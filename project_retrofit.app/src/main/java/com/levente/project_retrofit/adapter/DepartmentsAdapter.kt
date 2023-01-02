package com.levente.project_retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levente.project_retrofit.R
import com.levente.project_retrofit.api.model.SettingsResponse

class DepartmentsAdapter(private var list: List<SettingsResponse>): RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView = itemView.findViewById(R.id.group_details_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_department_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.personName.text = currentItem.firstName + " " + currentItem.lastName
    }

    override fun getItemCount() = list.size

    fun setData(newList: ArrayList<SettingsResponse>) {
        list = newList
    }
}