package com.buidanhtuan.saveme.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.buidanhtuan.saveme.R

internal class NoteAdapter internal constructor(context: Context, private val resource: Int, private val itemList: ArrayList<String>?) : ArrayAdapter<NoteAdapter.ItemHolder>(context, resource) {

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()
            holder.name = convertView!!.findViewById(R.id.textView)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

        holder.name!!.text = this.itemList!![position]

        return convertView
    }

    internal class ItemHolder {
        var name: TextView? = null
    }
}
