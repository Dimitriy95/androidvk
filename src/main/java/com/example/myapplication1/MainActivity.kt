package com.example.myapplication1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SqAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val initialCount = resources.getInteger(R.integer.initial_count)
        val count = savedInstanceState?.getInt(COUNT_KEY) ?: initialCount

        adapter = SqAdapter(count)

        val list = findViewById<RecyclerView>(R.id.squares_list)

        list.layoutManager = GridLayoutManager(
            this,
            resources.getInteger(R.integer.columns)
        )

        list.adapter = adapter

        findViewById<Button>(R.id.add_button).setOnClickListener {
            adapter.addSq()
            list.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNT_KEY, adapter.itemCount)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val COUNT_KEY = "squares_count"
    }
}