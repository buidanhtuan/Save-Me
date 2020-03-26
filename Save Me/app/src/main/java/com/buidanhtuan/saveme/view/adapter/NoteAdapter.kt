package com.buidanhtuan.saveme.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note

internal class NoteAdapter internal constructor(context: Context, private val resource: Int, private val itemList: ArrayList<Note>?) : ArrayAdapter<NoteAdapter.ItemHolder>(context, resource) {

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()
            holder.title = convertView!!.findViewById(R.id.textView)
            holder.content = convertView!!.findViewById(R.id.textView2)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }
        holder.title!!.text = this.itemList!![position].title
        holder.content!!.text = this.itemList[position].content
        return convertView
    }

    internal class ItemHolder {
        var title: TextView? = null
        var content: TextView? = null
    }
}
