package com.buidanhtuan.saveme.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.buidanhtuan.saveme.MainActivity

import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view.adapter.NoteAdapter
import com.buidanhtuan.saveme.view.fragment.TextFragment
import com.buidanhtuan.saveme.view_model.data_base.Query
import com.example.flatdialoglibrary.dialog.FlatDialog
import kotlinx.android.synthetic.main.layout_content_nav.*
import kotlinx.android.synthetic.main.layout_content_nav.fab1
import kotlinx.android.synthetic.main.layout_content_nav.gridview
import kotlinx.android.synthetic.main.layout_content_nav.menu_red
import kotlinx.android.synthetic.main.layout_search.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainFragment : Fragment() {

    private var listNote: ArrayList<Note> = ArrayList()
    private var data: MutableList<Note> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
        expandable.setOnExpandListener {
            if (it) {
                toast("expanded")
            } else {
                toast("collapse")
            }
        }
        search.setOnClickListener {
            if (expandable.isExpanded) {
                expandable.collapse()
                search_view.isIconified = true
            } else {
                expandable.expand()
                search_view.isIconified = false
            }
        }
        list_frame.setOnClickListener {
            if (expandable.isExpanded) {
                expandable.collapse()
                search_view.isIconified = true
                search_view.isIconified = true
            }
        }
        updateListNote()
//        gridview.setOnItemClickListener { parent, view, position, id ->
//            val view = view.findViewById<TextView>(R.id.data_sound)
//            if(view !=null){
//                var mediaPlayer : MediaPlayer? = null
//                mediaPlayer = MediaPlayer.create(activity as MainActivity, Uri.parse(view.text.toString()))
//                mediaPlayer?.run {
//                    start()
//                }
//            }
//        }
        gridview.setOnItemLongClickListener { parent, v, position, id ->
            showEditDialog(v)
            false
        }
        floatingClick()
    }
    //xử lí nút thêm note
    private fun floatingClick(){
        menu_red.setClosedOnTouchOutside(true)
        fab1.setOnClickListener {
            (activity as MainActivity).setFragment(TextFragment())
            menu_red.close(true)
        }
//        fab2.setOnClickListener {
//            (activity as MainActivity).setFragment(ImageFragment())
//            menu_red.close(true)
//        }
//        fab3.setOnClickListener {
//            (activity as MainActivity).setFragment(SoundFragment())
//            menu_red.close(true)
//        }
//        fab4.setOnClickListener {
//            (activity as MainActivity).setFragment(SpeedFragment())
//            menu_red.close(true)
//        }
    }
    //cập nhật trạng thái của listNote sau khi thêm, sửa, xóa
    private fun updateListNote(){
        data = Query.getAllNoteOfUser(1)
        listNote.clear()
        for(i in 0 until data.size){
            listNote.add(data[i])
        }
        val adapter = NoteAdapter(
            activity as MainActivity,
            listNote
        )
        gridview.adapter = adapter
    }
    //tạo cửa sổ để edit note
    private fun showEditDialog(v : View){
        val flatDialog = FlatDialog(activity as MainActivity)
        flatDialog.setCanceledOnTouchOutside(true)
        flatDialog.setTitle("Send a message")
            .setTitleColor(Color.parseColor("#000000"))
            .setBackgroundColor(Color.parseColor("#f5f0e3"))
            .setFirstButtonColor(Color.parseColor("#fda77f"))
            .setFirstButtonTextColor(Color.parseColor("#000000"))
            .setFirstButtonText("edit")
            .setSecondButtonColor(Color.parseColor("#fda77f"))
            .setSecondButtonTextColor(Color.parseColor("#000000"))
            .setSecondButtonText("delete")
            .show()
        flatDialog.withFirstButtonListner {
            flatDialog.dismiss()
        }
        //xóa note
        flatDialog.withSecondButtonListner {
            val id = v.findViewById<TextView>(R.id.id).text.toString().toInt()
            Query.deleteNote(id)
            updateListNote()
            flatDialog.dismiss()
        }
    }
    private fun toast(text: String) {
        Toast.makeText(activity as MainActivity, text, Toast.LENGTH_SHORT).show()
    }
}
