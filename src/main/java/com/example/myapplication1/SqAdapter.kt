package com.example.myapplication1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class SqAdapter(private var count: Int) :
    RecyclerView.Adapter<SqAdapter.SqViewHolder>() {

    class SqViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.findViewById(R.id.sq_id)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SqViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.square, parent, false)

        return SqViewHolder(view)
    }

    override fun onBindViewHolder(holder: SqViewHolder, position: Int) {
        holder.number.text = position.toString()

        val color = if (position % 2 == 0) {
            R.color.chet_sq
        } else {
            R.color.ne_chet_sq
        }

        holder.number.setBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, color)
        )
    }

    override fun getItemCount(): Int {
        return count
    }

    fun addSq() {
        count++
        notifyItemInserted(count - 1)
    }
}