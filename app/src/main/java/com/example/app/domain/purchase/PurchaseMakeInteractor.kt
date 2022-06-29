package com.example.app.domain.purchase



interface PurchaseMakeInteractor {
    suspend fun makePurchase(product: ProductEntity)
}
