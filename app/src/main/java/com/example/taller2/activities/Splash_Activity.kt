package com.example.taller2.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R


class Splash_Activity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Log.d("SplashActivity" ,"Iniciando Splash en la palicación")

        //configurar el temporizador de redireccion
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, IndexActivity::class.java)
            startActivity(intent)
            finish()
        },SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity", "Onstart: Activity Splash esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SplashActivity", "OnResumen: Activity Splash esta en primer plano")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SplashActivity", "OnPause: Activity Splash esta en primer plano")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "OnDestroy: Activity Splash esta en primer plano")

    }
}