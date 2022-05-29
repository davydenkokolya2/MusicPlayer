package com.github.krottv.tmstemp.view

import android.graphics.Outline
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks

class TracksAdapter(data: List<Tracks>) : RecyclerView.Adapter<TracksViewHolder>() {

    var data: List<Tracks> = data
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)

        return TracksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val item = data[position]
        holder.imageTrack.load(item.image.replace("{w}", "200").replace("{h}", "200"))
        holder.titleTrack.text = item.title
        holder.textTrack.text = item.artist

        holder.imageTrack.clipToOutline = true
        holder.imageTrack.outlineProvider = object: ViewOutlineProvider() {
            override fun getOutline(p0: View, p1: Outline) {
                p1.setRoundRect(0, 0, p0.width, p0.height, 8.0F)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}