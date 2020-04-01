package com.buidanhtuan.saveme.view.adapter

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Messenger
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.net.toUri
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view.activity.MainActivity
import com.buidanhtuan.saveme.view_model.database.DatabaseHelper

@Suppress("NAME_SHADOWING", "CAST_NEVER_SUCCEEDS")
internal class NoteAdapter internal constructor
    (context: Context, private val resource: Int, private val itemList: ArrayList<Note>?)
    : ArrayAdapter<NoteAdapter.ItemHolder>(context, resource) {

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
            holder.content = convertView.findViewById(R.id.textView2)
            holder.id = convertView.findViewById(R.id.textView3)
            holder.image = convertView.findViewById(R.id.imageView3)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }
        holder.title!!.text = this.itemList!![position].title
        holder.content!!.text = this.itemList[position].content
        holder.id!!.text = this.itemList[position].id.toString()
        holder.image?.setImageURI(Uri.parse(this.itemList[position].image))
        return convertView

    }

    internal class ItemHolder {
        var title: TextView? = null
        var content: TextView? = null
        var id: TextView? = null
        var image : ImageView? = null
    }
}
