package com.example.wisproapi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wisproapi.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppThemeV2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }
}
