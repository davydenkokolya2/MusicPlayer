package com.example.app.view.albumsrecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.view.R


class AlbumsViewHolder(view: View): RecyclerView.ViewHolder(view){
    val imageAlbum = view.findViewById<ImageView>(R.id.imageAlbum)
    val textAlbum = view.findViewById<TextView>(R.id.albumText)
}