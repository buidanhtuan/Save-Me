package com.buidanhtuan.saveme.view.activity

import android.Manifest
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.view.fragment.ListNoteFragment
import com.buidanhtuan.saveme.view_model.database.DatabaseHelper
import com.google.android.material.navigation.NavigationView
import com.kotlinpermissions.KotlinPermissions

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private val fManager = supportFragmentManager
    private val fTransaction = fManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // gán data vào app
        DatabaseHelper.initDatabaseInstance(this)
        //khởi tạo các view cơ bản (toolbar,navigation)
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
        //khởi tạo view ở frame
        fTransaction.add(R.id.main_frame,ListNoteFragment())
        fTransaction.commit()
        permission()
    }
    private fun permission(){
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.CAMERA)
            .onAccepted {
            }
            .onDenied {
            }
            .onForeverDenied {
            }
            .ask()
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .onAccepted {
            }
            .onDenied {
            }
            .onForeverDenied {
            }
            .ask()
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onAccepted {
            }
            .onDenied {
            }
            .onForeverDenied {
            }
            .ask()
    }
    //xử lý click ở navigation
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
    //chuyển màn hình
    fun setFragment(fragment: Fragment){
        val fragmentTransaction = fManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_from_right,
            R.anim.slide_out_to_left,
            R.anim.slide_in_from_left,
            R.anim.slide_out_to_right)
        fragmentTransaction.replace(R.id.main_frame,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    fun setBackFragment(fragment: Fragment){
        val fragmentTransaction = fManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_from_left,
            R.anim.slide_out_to_right ,
            R.anim.slide_in_from_right,
            R.anim.slide_out_to_left)
        fragmentTransaction.replace(R.id.main_frame,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
