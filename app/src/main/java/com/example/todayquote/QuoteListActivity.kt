package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class QuoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        val currentQuoteSize = intent.getIntExtra("quote_size", 0)

        Toast.makeText(this, "현재 ${currentQuoteSize}개의 명언이 저장되어 있습니다.", Toast.LENGTH_SHORT).show()

    }
}