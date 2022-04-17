package com.example.todayquote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private val dataList : List<Quote>) : RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>() {

    class QuoteItemViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        // 해당 뷰에서 보여줄 명언 데이터를 저장할 속성
        lateinit var quote : Quote

        // 명언 데이터를 보여줄 내부 뷰
        val quoteText = view.findViewById<TextView>(R.id.quote_text)
        val quoteFrom = view.findViewById<TextView>(R.id.quote_from)

        fun bind(q : Quote) {
            this.quote = q

            quoteText.text = quote.text
            quoteFrom.text = quote.from
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : QuoteItemViewHolder{

        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return QuoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int = R.layout.quote_list_item
}