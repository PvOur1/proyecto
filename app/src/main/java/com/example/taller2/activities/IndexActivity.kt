package com.example.taller2.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class IndexActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        Log.d("MainActivity", "onCreate ejecutado")

        val btnRedi = findViewById<Button>(R.id.btnComienza_pg2)
        val tvRegistrar = findViewById<TextView>(R.id.textoregistar_main)

        tvRegistrar.setOnClickListener {
            Log.d("MainActivity", "Navegando a RegisterActivity")
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnRedi.setOnClickListener {
            Log.d("MainActivity", "Navegando a LoginActivity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop ejecutado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy ejecutado")
    }
}
