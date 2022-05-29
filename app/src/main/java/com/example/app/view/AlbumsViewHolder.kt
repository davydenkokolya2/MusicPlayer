package com.github.krottv.tmstemp.view

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class AlbumsViewHolder(view: View): RecyclerView.ViewHolder(view){
    val imageAlbum = view.findViewById<ImageView>(R.id.imageAlbum)
    val textAlbum = view.findViewById<TextView>(R.id.albumText)
}