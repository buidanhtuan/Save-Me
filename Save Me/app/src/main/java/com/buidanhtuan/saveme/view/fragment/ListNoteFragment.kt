package com.buidanhtuan.saveme.view.fragment

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view.activity.MainActivity
import com.buidanhtuan.saveme.view.adapter.NoteAdapter
import com.buidanhtuan.saveme.view_model.database.DatabaseHelper
import com.example.flatdialoglibrary.dialog.FlatDialog
import kotlinx.android.synthetic.main.adapter_note_sound.*
import kotlinx.android.synthetic.main.fragment_list_note.*

class ListNoteFragment : Fragment() {
    private var listNote: ArrayList<Note> = ArrayList()
    private var data: MutableList<Note> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    //gán view cho class
    private fun initView(){
        updateListNote()
        gridview.setOnItemClickListener { parent, view, position, id ->
            val view = view.findViewById<TextView>(R.id.title_sound)
            if(view !=null){
                var mediaPlayer : MediaPlayer? = null
                mediaPlayer = MediaPlayer.create(activity as MainActivity, Uri.parse(data_sound.text.toString()))
                mediaPlayer?.run {
                    start()
                }
            }
        }
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
            showInputNote()
            menu_red.close(true)
        }
        fab2.setOnClickListener {
            (activity as MainActivity).setFragment(ImageFragment())
            menu_red.close(true)
        }
        fab3.setOnClickListener {
            (activity as MainActivity).setFragment(SoundFragment())
            menu_red.close(true)
        }
    }
    //cập nhật trạng thái của listNote sau khi thêm, sửa, xóa
    private fun updateListNote(){
        data = DatabaseHelper.getAllData()
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
            DatabaseHelper.deleteData(id)
            updateListNote()
            flatDialog.dismiss()
        }
    }
    //tạo cửa sổ để them note mới
    private fun showInputNote() {
        val flatDialog = FlatDialog(activity as MainActivity)
        flatDialog.setCanceledOnTouchOutside(true)
        flatDialog.setTitle("Send a message")
            .setTitleColor(Color.parseColor("#000000"))
            .setBackgroundColor(Color.parseColor("#f5f0e3"))
            .setFirstTextFieldHint("title")
            .setFirstTextFieldTextColor(Color.parseColor("#000000"))
            .setFirstTextFieldHintColor(Color.parseColor("#000000"))
            .setFirstTextFieldBorderColor(Color.parseColor("#000000"))
            .setLargeTextFieldHint("write your content here ...")
            .setLargeTextFieldHintColor(Color.parseColor("#000000"))
            .setLargeTextFieldBorderColor(Color.parseColor("#000000"))
            .setLargeTextFieldTextColor(Color.parseColor("#000000"))
            .setFirstButtonColor(Color.parseColor("#fda77f"))
            .setFirstButtonTextColor(Color.parseColor("#000000"))
            .setFirstButtonText("Done")
            .show()
        //Lưu note vào database và cập nhật lên màn hình
        flatDialog.withFirstButtonListner {
            val note = Note(listNote.size,"text",flatDialog.firstTextField.toString(),flatDialog.largeTextField.toString(),"","")
            DatabaseHelper.insertData(note)
            updateListNote()
            flatDialog.dismiss()
        }
    }
}
