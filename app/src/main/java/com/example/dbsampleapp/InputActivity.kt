package com.example.dbsampleapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dbsampleapp.databinding.ActivityInputBinding
import com.example.dbsampleapp.model.ContentEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding

    private val viewModel : InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@InputActivity
            viewModel = this@InputActivity.viewModel
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (intent.getSerializableExtra(ITEM) as? ContentEntity)?.let {
            viewModel.initData(it)
        }

        viewModel.doneEvent.observe(this) {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        private const val ITEM = "item"

        fun start(context: Context, item: ContentEntity? = null) {
            Intent(context, InputActivity::class.java).apply {
                putExtra(ITEM, item)
            }.run {
                context.startActivity(this)
            }
        }
    }
}