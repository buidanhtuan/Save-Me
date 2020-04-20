package com.buidanhtuan.saveme.view.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptIntrinsicYuvToRGB
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.saveme.MainActivity

import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view.MainFragment
import com.buidanhtuan.saveme.view.activity.TimeActivity
import com.buidanhtuan.saveme.view.adapter.CheckListAdapter
import com.buidanhtuan.saveme.view_model.data_base.Query
import com.example.flatdialoglibrary.dialog.FlatDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.listener.ColorListener
import com.github.dhaval2404.colorpicker.util.setVisibility
import kotlinx.android.synthetic.main.adapter_text.*
import kotlinx.android.synthetic.main.fragment_text.*

class TextFragment : Fragment() {
    private var themeColor = ""
    private var note = Note(0,1,"text","","","","#123456","","")
    lateinit var recyclerView : RecyclerView
    lateinit var adapter: CheckListAdapter
    var list : ArrayList<String> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
        setTag()
        setTheme()
        setCheckList()
        setTime()
        saveData()
    }
    private fun setTheme() {
        bt_text_theme.setOnClickListener { _ ->
            MaterialColorPickerDialog
                .Builder(activity!!)
                .setColorRes(resources.getStringArray(R.array.themeColorHex).map {
                    Color.parseColor(
                        it
                    )
                }.toList())
                .setDefaultColor(themeColor)
                .setColorListener(object : ColorListener {
                    override fun onColorSelected(color: Int, colorHex: String) {
                        themeColor = colorHex
                        bt_text_theme.backgroundTintList = ColorStateList.valueOf(color)
                        layout_text.setBackgroundColor(color)
                        note.theme = color
                    }
                })
                .showBottomSheet(childFragmentManager)
        }
    }
    private fun setTag() {
        bt_text_tag.setOnClickListener {
            val flatDialog = FlatDialog(activity as MainActivity)
            flatDialog.setCanceledOnTouchOutside(true)
            flatDialog.setTitle("Hãy viết tag")
                .setTitleColor(Color.parseColor("#000000"))
                .setBackgroundColor(Color.parseColor("#f5f0e3"))
                .setFirstTextFieldHint("hãy nhập tag")
                .setFirstTextFieldBorderColor(Color.parseColor("#000000"))
                .setFirstTextFieldTextColor(Color.parseColor("#000000"))
                .setFirstButtonColor(Color.parseColor("#fda77f"))
                .setFirstButtonTextColor(Color.parseColor("#000000"))
                .setFirstButtonText("Lưu")
                .show()
            flatDialog.withFirstButtonListner {
                tv_text_tag.text = "#" +flatDialog.firstTextField.toString()
                note.tag = tv_text_tag.text.toString()
                flatDialog.dismiss()
            }
        }
    }
    private fun setTime() {
        ib_text_time.setOnClickListener {
            val intent: Intent = Intent(activity as MainActivity, TimeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setCheckList(){
        bt_text_list.setOnClickListener {
            addItem()
        }
        for (i in 0 until list.size){
            note.status = note.status+list[i] + "*"
        }
    }
    private fun addItem(){
        list.add("")
        recyclerView = rv_text_list
        adapter = CheckListAdapter(list,activity as MainActivity)
        rv_text_list.adapter = adapter
    }
    private fun saveData(){
        bt_text_save.setOnClickListener {
            note.title      = et_text_title.text.toString()
            note.content    = et_text_content.text.toString()
            Query.insertNote(note)
            (activity as MainActivity).setFragment(MainFragment())
            System.out.println(Query.getAllNoteOfUser(1))
        }
    }
}
