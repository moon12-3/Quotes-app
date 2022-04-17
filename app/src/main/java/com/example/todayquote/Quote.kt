package com.example.todayquote
import android.content.SharedPreferences

data class Quote(var idx:Int, var text:String, var from:String="") {
    companion object {
        // 명언을 저장하고 수정하는 함수
        fun saveToPreference(pref : SharedPreferences, idx: Int, text:String, from: String="") : Quote {
            val editor = pref.edit()

            editor.putString("${idx}.text", text)
            editor.putString("${idx}.from", from.trim())    //trim으로 공백제거

            editor.apply()

            return Quote(idx, text, from)
        }

        // 모든 명언을 출력하는 함수
        fun getQuotesFromPreferences(pref : SharedPreferences) : MutableList<Quote> {

            val quotes = mutableListOf<Quote>()

            for(i in 0 until 20) {
                val quoteText = pref.getString("${i}.text", "")!!
                val quoteFrom = pref.getString("${i}.from", "")!!

                if(quoteText.isNotBlank()) {
                    quotes.add(Quote(i, quoteText, quoteFrom))
                }
            }

            return quotes
        }

        // 명언을 지우는 함수
        fun removeQuoteFromPreferences(pref: SharedPreferences, idx:Int) {

            val editor = pref.edit()

            editor.remove("${idx}.text")
            editor.remove("${idx}.from")

            editor.apply()
        }
    }
}