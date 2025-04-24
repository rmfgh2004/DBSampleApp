package com.example.dbsampleapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dbsampleapp.databinding.ActivityLoginBinding
import androidx.core.content.edit

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }

    private fun init() {
        loadInformation()
        binding.loginButton.setOnClickListener { login() }
    }

    // login logic
    private fun login() {
        if(validation()) {
            saveInformation()
            MainActivity.start(this)
        }
    }

    // login validation(id, pw)
    private fun validation() : Boolean {

        binding.idTextInputLayout.error = null
        binding.passwordTextInputLayout.error = null

        val id = binding.idEditText.text.toString()
        val pw = binding.passwordEditText.text.toString()

        if (id == "" || pw == "") {

            if (id == "") binding.idTextInputLayout.error = "ID is empty"
            if (pw == "") binding.passwordTextInputLayout.error = "PW is empty"

            return false
        }

        return true
    }

    // SharedPreference (id and pw) save
    private fun saveInformation() {
        val prefs = getSharedPreferences(getString(R.string.com_example_dbsampleapp_pref_name), Context.MODE_PRIVATE)

        prefs.edit(true) {
            putString("id", binding.idEditText.text.toString())
            putString("pw", binding.passwordEditText.text.toString())
        }
    }

    private fun loadInformation() {
        val prefs = getSharedPreferences(getString(R.string.com_example_dbsampleapp_pref_name), Context.MODE_PRIVATE)
        binding.idEditText.setText(prefs.getString("id", ""))
        binding.passwordEditText.setText(prefs.getString("pw", ""))
    }


}