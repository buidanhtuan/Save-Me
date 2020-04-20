package com.buidanhtuan.saveme.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.R.drawable.cancel
import com.buidanhtuan.saveme.R.drawable.ic_launcher_background
import com.buidanhtuan.saveme.model.Note

@Suppress("NAME_SHADOWING", "CAST_NEVER_SUCCEEDS")
internal class NoteAdapter internal constructor
    (context: Context, internal val listNote: ArrayList<Note>?)
    : ArrayAdapter<NoteAdapter.ItemHolder>(context,0) {

    override fun getCount(): Int {
        return if (this.listNote != null) this.listNote.size else 0
    }

    @SuppressLint("Range")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var holder: ItemHolder? = null
        if (convertView == null) {
            if (listNote?.get(position)!!.type == "text"){
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_text, null)
                holder = ItemHolder()
                holder.id       = convertView.findViewById(R.id.id)
                holder.title    = convertView!!.findViewById(R.id.adapter_text_title)
                holder.content  = convertView.findViewById(R.id.adapter_text_content)
                holder.tag      = convertView.findViewById(R.id.adapter_text_tag)
                convertView.tag = holder
            }
        } else {
            holder = convertView.tag as ItemHolder
        }
        holder?.id!!.text       = this.listNote!![position].id.toString()
        holder.title!!.text     = this.listNote[position].title
        holder.content!!.text   = this.listNote[position].content
        holder.tag!!.text       = this.listNote[position].tag
        convertView?.setBackgroundColor(listNote[position].theme)
        return convertView!!
    }
    internal class ItemHolder {
        var id      : TextView? = null
        var user_id : TextView? = null
        var type    : TextView? = null
        var title   : TextView? = null
        var content : TextView? = null
        var data    : TextView? = null
        var status  : TextView? = null
        var time    : TextView? = null
        var tag     : TextView? = null
    }
}
