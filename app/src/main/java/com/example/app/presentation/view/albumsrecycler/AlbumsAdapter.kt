package com.example.app.presentation.view.albumsrecycler

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.example.app.domain.AlbumModel
import com.example.app.view.R

class AlbumsAdapter(data: List<AlbumModel>) : RecyclerView.Adapter<AlbumsViewHolder>() {

    var data: List<AlbumModel> = data
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return AlbumsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = data[position]
        holder.imageAlbum.load(item.image)
        holder.textAlbum.text = item.name

        holder.imageAlbum.clipToOutline = true
        holder.imageAlbum.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(p0: View, p1: Outline) {
                p1.setRoundRect(0, 0, p0.width, p0.height, 10.0F)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}