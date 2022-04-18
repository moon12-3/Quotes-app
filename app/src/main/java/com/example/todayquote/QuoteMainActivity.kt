package com.example.todayquote

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class QuoteMainActivity : AppCompatActivity() {

    // 명언을 저장할 배열 선언
    private lateinit var quotes:MutableList<Quote>
    private lateinit var pref:SharedPreferences

    fun initializeQuotes() {
        val initialized = pref.getBoolean("initialized", false)
        if(!initialized) {
            Quote.saveToPreference(pref, 0, "창조적인 삶을 사려면 내가 틀릴지도 모른다는 공포를 버려야한다")
            Quote.saveToPreference(pref, 1, "육지에 사는 생물들은 언제나 바다를 그리워하지만 그 바닷물에 잠긴 순간 돌아갈 수 없다.", "어두운 바다의 등불이 되어 中")
            Quote.saveToPreference(pref, 2, "직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다.", "엘버트 허버드")
            Quote.saveToPreference(pref, 3, "내일은 내일의 태양이 뜬다.", "바람과 함께 사라지다 中")
            Quote.saveToPreference(pref, 4, "네 믿음은 네 생각이 된다 . 네 생각은  네 말이 된다. 네말은 네 행동이 된다 네행동은 네 습관이된다 . 네 습관은 네 가치가 된다 . 네 가치는 네 운명이 된다", "간디")

            val editor = pref.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        initializeQuotes()

        // 명언과 출처 텍스트 뷰
        var quoteText = findViewById<TextView>(R.id.quote_text)
        val quoteFrom = findViewById<TextView>(R.id.quote_from)
        // 명언 보기 버튼 뷰
        val toQuoteListButton = findViewById<Button>(R.id.quote_list_btn)
        // 명언 수정 버튼 뷰
        val toQuoteEditButton = findViewById<Button>(R.id.quote_edit_btn)

        toQuoteListButton.setOnClickListener {
            val intent = Intent(this, QuoteListActivity::class.java)

            intent.putExtra("quote_size", quotes.size)
            startActivity(intent)
        }

        toQuoteEditButton.setOnClickListener {
            val intent = Intent(this, QuoteEditActivity::class.java)
            startActivity(intent)
        }

        quotes = Quote.getQuotesFromPreferences(pref)

        if(quotes.isNotEmpty()) {
            var randomIndex = Random().nextInt(quotes.size)
            var randomQuote = quotes[randomIndex]
            quoteText.text = randomQuote.text
            quoteFrom.text = randomQuote.from
        }
        else {
            quoteText.text = "저장된 명언이 없습니다."
            quoteFrom.text = ""
        }

    }
}