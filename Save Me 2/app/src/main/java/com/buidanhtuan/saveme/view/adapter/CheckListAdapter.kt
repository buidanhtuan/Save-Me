package com.buidanhtuan.saveme.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.saveme.R

class CheckListAdapter (val list: ArrayList<String>, var context: Context) :
    RecyclerView.Adapter<CheckListAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_check_list,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = list[position]
        holder.textView.text = item
    }
    class RecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val checkBox : CheckBox = itemView.findViewById(R.id.checkbox)
        val textView : TextView = itemView.findViewById(R.id.text_view)
    }
}
