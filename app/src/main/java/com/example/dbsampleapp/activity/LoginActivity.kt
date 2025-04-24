package com.example.dbsampleapp.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dbsampleapp.databinding.ActivityLoginBinding
import androidx.core.content.edit
import com.example.dbsampleapp.AppDatabase
import com.example.dbsampleapp.R
import com.example.dbsampleapp.dao.UserDao
import com.example.dbsampleapp.entity.User

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val userDao: UserDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.userDao()
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

        val user = userDao.findById(id)
        if (user != null) {
            if (user.password != pw) {
                binding.passwordTextInputLayout.error = "PW is different"
                return false
            } else {
                return true
            }
        }

        userDao.insert(User(0, id, pw))
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