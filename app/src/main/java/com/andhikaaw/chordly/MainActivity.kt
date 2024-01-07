package com.andhikaaw.chordly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.andhikaaw.chordly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replacefragment(Home())
        binding.bottomNavigateView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replacefragment(Home())
                R.id.profile -> replacefragment(Profile())
                R.id.setting -> replacefragment(Setting())

                else ->{

                }
            }
            true
        }
    }
    private fun replacefragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.frameLayout, fragment
        )
        fragmentTransaction.commit()
    }
}