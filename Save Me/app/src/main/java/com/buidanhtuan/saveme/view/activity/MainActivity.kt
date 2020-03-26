package com.buidanhtuan.saveme.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.view.adapter.NoteAdapter
import com.example.flatdialoglibrary.dialog.FlatDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.layout_floating_menu.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val itemList: ArrayList<String> = ArrayList()
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            menu.close(true)
        }
    }
    private fun updateGridview(){

        val adapter = NoteAdapter(
            this,
            R.layout.adapter_note,
            itemList
        )
        gridview.adapter = adapter

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            Toast.makeText(this@MainActivity, " Clicked Position: " + (position + 1),
                Toast.LENGTH_SHORT).show()
        }
    }
    private fun showLargeInputDialog() {
        val flatDialog = FlatDialog(this)
        flatDialog.setCanceledOnTouchOutside(true)
        flatDialog.setTitle("Send a message")
            .setTitleColor(Color.parseColor("#000000"))
            .setBackgroundColor(Color.parseColor("#f5f0e3"))
            .setLargeTextFieldHint("write your text here ...")
            .setLargeTextFieldHintColor(Color.parseColor("#000000"))
            .setLargeTextFieldBorderColor(Color.parseColor("#000000"))
            .setLargeTextFieldTextColor(Color.parseColor("#000000"))
            .setFirstButtonColor(Color.parseColor("#fda77f"))
            .setFirstButtonTextColor(Color.parseColor("#000000"))
            .setFirstButtonText("Done")
            .show()
        flatDialog.withFirstButtonListner {
            itemList.add(flatDialog.largeTextField.toString())
            updateGridview()
            flatDialog.dismiss()
        }
    }
}
