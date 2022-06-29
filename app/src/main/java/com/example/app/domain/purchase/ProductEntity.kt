package com.example.app.domain.purchase


data class ProductEntity(
    val periodType: PeriodType,
    val priceLocal: String,
    val originalObject: Any
)
