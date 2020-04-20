package com.buidanhtuan.saveme

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.buidanhtuan.saveme.model.User
import com.buidanhtuan.saveme.view.MainFragment
import com.buidanhtuan.saveme.view_model.data_base.Query
import com.kotlinpermissions.KotlinPermissions

class MainActivity : AppCompatActivity() {
    //fragment
    private val fManager = supportFragmentManager
    private val fTransaction = fManager.beginTransaction()
    //bacck pressed
    private var backPressedTime : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fTransaction.add(R.id.main_frame,MainFragment())
        fTransaction.commit()

        Query.initDatabaseInstance(this)
        Query.insertUser(User(0,"",""))

        permission()

        supportActionBar?.hide()
        actionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

//    override fun onBackPressed() {
//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            super.onBackPressed()
//            return;
//        } else {
//            Toast.makeText(baseContext,"ấn lần nữa để thoát", Toast.LENGTH_SHORT).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//    }

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
    private fun permission() {
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.CAMERA)
            .onAccepted {}
            .onDenied {}
            .onForeverDenied {}
            .ask()
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .onAccepted {}
            .onDenied {}
            .onForeverDenied {}
            .ask()
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onAccepted {}
            .onDenied {}
            .onForeverDenied {}
            .ask()
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.RECORD_AUDIO)
            .onAccepted {}
            .onDenied {}
            .onForeverDenied {}
            .ask()
    }
}
