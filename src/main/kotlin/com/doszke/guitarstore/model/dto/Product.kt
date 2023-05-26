package com.doszke.guitarstore.model.dto

class ProductDTO(
        val id: Long?,
        val name: String?,
        val productDetails: ProductDetailsDTO?,
        val productCategoryId: Long?
)

class ProductDetailsDTO(
        val id: Long?,
        val description: String?,
        val price: Double?,
        val productId: Long?
)

class ProductCategoryDTO(
        val id: Long?,
        val name: String?,
        val products: List<ProductDTO>
)