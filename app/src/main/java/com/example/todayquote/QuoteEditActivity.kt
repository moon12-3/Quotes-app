package com.example.todayquote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_edit)

        val pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        val quotes = Quote.getQuotesFromPreferences(pref)

        val editQuotes = mutableListOf<Quote>()
        for (i in 0 until 20) {
            editQuotes.add(Quote(i,"",""))
        }
        for(q in quotes) {
            editQuotes[q.idx].idx = q.idx
            editQuotes[q.idx].text = q.text
            editQuotes[q.idx].from = q.from
        }

        val layoutManager = LinearLayoutManager(this)
        val adapter = QuoteEditAdapter(editQuotes)

        val recyclerView = findViewById<RecyclerView>(R.id.quote_edit)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }
}