package com.example.app.view.tracksrecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class TracksViewHolder(view: View): RecyclerView.ViewHolder(view){
    val imageTrack = view.findViewById<ImageView>(R.id.imageTrack)
    val titleTrack = view.findViewById<TextView>(R.id.titleTrack)
    val textTrack = view.findViewById<TextView>(R.id.textTrack)
}