package com.example.admin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.admin.data.models.Category

class SpinnerAdapter(
    private val context: Context,
    private val textViewResourceId: Int,
    private val categories: ArrayList<Category>
) : ArrayAdapter<Category>(context, textViewResourceId, categories) {


    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Category? {
        return categories[position]
    }

    override fun getItemId(position: Int): Long {
        return categories[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.text = categories[position].name
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label =  super.getDropDownView(position, convertView, parent) as TextView
        label.text = categories[position].name
        return label
    }
    fun updateList(newList: List<Category>){
        categories.clear()
        categories.addAll(newList)
        notifyDataSetChanged()
    }
}