package com.doszke.guitarstore.model.factory

import com.doszke.guitarstore.model.Product
import com.doszke.guitarstore.model.ProductDetails
import org.springframework.stereotype.Component

@Component
class ProductFactory {

    fun modifyDetails(oldDetails: ProductDetails, newDetails: ProductDetails): ProductDetails {
        val id = oldDetails.id
        val description = newDetails.description ?: oldDetails.description
        val price = newDetails.price ?: oldDetails.price
        val product = newDetails.product ?: oldDetails.product
        return ProductDetails(id, description, price, product)
    }

}