package com.buidanhtuan.saveme.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.buidanhtuan.saveme.R
import com.buidanhtuan.saveme.view.MainFragment

class LoginActivity : AppCompatActivity() {
    //fragment
    private val fManager = supportFragmentManager
    private val fTransaction = fManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        fTransaction.add(R.id.main_frame, )
        fTransaction.commit()
    }
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
}
