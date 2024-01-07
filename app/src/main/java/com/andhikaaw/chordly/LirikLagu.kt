package com.andhikaaw.chordly

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.andhikaaw.chordly.database.Lagu
import com.andhikaaw.chordly.database.LaguApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LirikLagu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lirik_lagu)
        setupListener()
    }

    fun inputData() {
        val judul = findViewById<EditText>(R.id.edit_judul)
        val lirikLagu = findViewById<EditText>(R.id.edit_lirik)
        CoroutineScope(Dispatchers.IO).launch {
            LaguApp(this@LirikLagu).getLaguDao().addLagu(
                Lagu(0, judul.text.toString(), lirikLagu.text.toString())
            )
        }
    }

    fun setupListener(){
        val btn: Button = findViewById(R.id.btn_save)
        btn.setOnClickListener {
            inputData()
            val showDialog = AlertDialog.Builder(this)
            showDialog.setMessage("Success!")
            showDialog.setPositiveButton("OK") { dialog, id ->
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = Home()
                fragmentTransaction.replace(R.id.fragment_home, fragment)
                fragmentTransaction.commit()
                finish()
            }
            showDialog.show()
        }
    }
}