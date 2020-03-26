package com.buidanhtuan.saveme.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.view.adapter.NoteAdapter
import com.buidanhtuan.saveme.view_model.database.DatabaseHelper
import com.example.flatdialoglibrary.dialog.FlatDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.layout_floating_menu.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var listNote: ArrayList<Note> = ArrayList()
    private var data: MutableList<Note> = ArrayList()
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DatabaseHelper.initDatabaseInstance(this)
        updateListNote()
        updateGridview()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        floatingClick()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun floatingClick(){
        item1.setOnClickListener {
            Toast.makeText(this,"item 1",Toast.LENGTH_LONG).show()
            showLargeInputDialog()
            menu.close(true)
        }
        item2.setOnClickListener {
            Toast.makeText(this,"item 1",Toast.LENGTH_LONG).show()
            val intent: Intent = Intent(this,ImageActivity::class.java)
            startActivity(intent)
            menu.close(true)
        }
    }
    private fun updateGridview(){
        val adapter = NoteAdapter(
            this,
            R.layout.adapter_note,
            listNote
        )
        gridview.adapter = adapter
        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            Toast.makeText(this@MainActivity, " Clicked Position: " + (position + 1),
                Toast.LENGTH_SHORT).show()
        }
        gridview.setOnItemLongClickListener { parent, v, position, id ->
            showEditDialog(v)
            false
        }
    }
    fun updateListNote(){
        data = DatabaseHelper.getAllData()
        for(i in 0 until data.size){
            listNote.add(data[i])
        }
    }
    private fun showLargeInputDialog() {
        val flatDialog = FlatDialog(this)
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
        flatDialog.withFirstButtonListner {
            var note = Note(listNote.size,flatDialog.firstTextField.toString(),flatDialog.largeTextField.toString())
            DatabaseHelper.insertData(note)
            listNote.add(note)
            updateGridview()
            flatDialog.dismiss()
        }
    }
    private fun showEditDialog(v : View){
        val flatDialog = FlatDialog(this)
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
        flatDialog.withSecondButtonListner {
            val a = v.findViewById<TextView>(R.id.textView3).text.toString().toInt()
            flatDialog.dismiss()
        }
    }
}
