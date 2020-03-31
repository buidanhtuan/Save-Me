package com.buidanhtuan.saveme.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView

import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view.activity.ImageActivity
import com.buidanhtuan.saveme.view.activity.MainActivity
import com.buidanhtuan.saveme.view.adapter.NoteAdapter
import com.buidanhtuan.saveme.view_model.database.DatabaseHelper
import com.example.flatdialoglibrary.dialog.FlatDialog
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
        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
        }
        gridview.setOnItemLongClickListener { parent, v, position, id ->
            showEditDialog(v)
            false
        }
        floatingClick()
    }
    //xử lí nút thêm note
    private fun floatingClick(){
        item1.setOnClickListener {
            showInputNote()
            menu.close(true)
        }
        item2.setOnClickListener {
            val intent = Intent(activity as MainActivity, ImageActivity::class.java)
            startActivity(intent)
            menu.close(true)
        }
    }
    //cập nhật trạng thái của listNote sau khi thêm, sửa, xóa
    private fun updateGridview(){
        data = DatabaseHelper.getAllData()
        listNote.clear()
        for(i in 0 until data.size){
            listNote.add(data[i])
        }
        val adapter = NoteAdapter(
            activity as MainActivity,
            R.layout.adapter_note,
            listNote
        )
        gridview.adapter = adapter
    }
    //tạo cửa sổ để edit note
    private fun showEditDialog(v : View){
        val flatDialog = FlatDialog(MainActivity())
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
        //cập nhật sữ liệu vào database
        flatDialog.withSecondButtonListner {
            val id = v.findViewById<TextView>(R.id.textView3).text.toString().toInt()
            DatabaseHelper.deleteData(id)
            updateGridview()
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
            val note = Note(listNote.size,flatDialog.firstTextField.toString(),flatDialog.largeTextField.toString())
            DatabaseHelper.insertData(note)
            updateGridview()
            flatDialog.dismiss()
        }
    }
}
