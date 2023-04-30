package com.doszke.guitarstore.repository

import com.doszke.guitarstore.model.Product
import com.doszke.guitarstore.model.ProductDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDetailsRepository: CrudRepository<ProductDetails, Long> {
    fun findByProduct(product: Product): ProductDetails?
}