package com.doszke.guitarstore.model

data class Product(
        val id: Long,
        val category: ProductCategory,
        val details: ProductDetails
)

data class ProductDetails(
        val id: Long,
        val description: String,
        val price: Double
)

data class ProductCategory(
        val id: Long,
        val name: String
)
