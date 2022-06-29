package com.example.app.presentation.view.purchase

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.view.R


class PurchaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val purchaseType: TextView = view.findViewById(R.id.purchaseType)
}