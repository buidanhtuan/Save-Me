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
    (context: Context, private val itemList: ArrayList<Note>?)
    : ArrayAdapter<NoteAdapter.ItemHolder>(context,0) {

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            if(itemList?.get(position)!!.type=="image"){
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_note_image, null)
                holder = ItemHolder()
                holder.title = convertView!!.findViewById(R.id.title_image)
                holder.id = convertView.findViewById(R.id.id)
                holder.image = convertView.findViewById(R.id.imageNote)
                convertView.tag = holder
            }
            else{
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_note_text, null)
                holder = ItemHolder()
                holder.title = convertView!!.findViewById(R.id.title_text)
                holder.content = convertView.findViewById(R.id.content_text)
                holder.id = convertView.findViewById(R.id.id)
                convertView.tag = holder
            }
        } else {
            holder = convertView.tag as ItemHolder
        }

        holder.title!!.text = this.itemList!![position].title
        holder.content?.text = this.itemList[position].content
        holder.id!!.text = this.itemList[position].id.toString()
        holder.image?.setImageURI(Uri.parse(this.itemList[position].image))
        return convertView

    }
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var convertView = convertView
//        if(itemList?.get(position)!!.type=="image"){
//            val holder : ImageHolder
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(resource, null)
//                holder = ImageHolder()
//                holder.titleImage = convertView!!.findViewById(R.id.title_image)
//                holder.idImage = convertView.findViewById(R.id.id_image)
//                holder.imageImage = convertView.findViewById(R.id.imageNote)
//                convertView.tag = holder
//            } else {
//                holder = convertView.tag as ImageHolder
//            }
//            holder.titleImage!!.text = this.itemList!![position].title
//            holder.idImage!!.text = this.itemList[position].id.toString()
//            holder.imageImage?.setImageURI(Uri.parse(this.itemList[position].image))
//        }
//        else{
//            val holder : TextHolder
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(resource, null)
//                holder = TextHolder()
//                holder.titleText = convertView!!.findViewById(R.id.title_text)
//                holder.idText = convertView.findViewById(R.id.id_text)
//                holder.contentText = convertView.findViewById(R.id.content_text)
//                convertView.tag = holder
//            } else {
//                holder = convertView.tag as TextHolder
//            }
//            holder.titleText!!.text = this.itemList!![position].title
//            holder.idText!!.text = this.itemList[position].id.toString()
//            holder.contentText!!.text = this.itemList[position].content
//        }
//        return convertView!!
//    }
    internal class ItemHolder {
        var title: TextView? = null
        var content: TextView? = null
        var id: TextView? = null
        var image : ImageView? = null
    }
}
