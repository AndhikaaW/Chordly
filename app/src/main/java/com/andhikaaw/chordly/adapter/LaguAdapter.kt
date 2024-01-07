package com.andhikaaw.chordly.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andhikaaw.chordly.R
import com.andhikaaw.chordly.database.Lagu

class LaguAdapter (private val lagu: ArrayList<Lagu>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<LaguAdapter.LaguViewHolder>() {

    class LaguViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val text_judul: TextView = view.findViewById(R.id.judul_lagu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaguViewHolder {
        return LaguViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_lagu, parent, false)
        )
    }

    override fun getItemCount() = lagu.size

    override fun onBindViewHolder(holder: LaguViewHolder, position: Int) {
        val lirik = lagu[position]
        holder.text_judul.text = lirik.judul
        holder.text_judul.setOnClickListener {
            listener.onClick(lirik)
        }
    }

    fun setData(list: List<Lagu>){
        lagu.clear()
        lagu.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(lirik : Lagu)
    }
}