package com.doszke.guitarstore.service

import com.doszke.guitarstore.model.Product
import com.doszke.guitarstore.model.ProductDetails
import com.doszke.guitarstore.repository.ProductDetailsRepository
import com.doszke.guitarstore.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductService(
        val productRepository: ProductRepository,
        val productDetailsRepository: ProductDetailsRepository
) {

    fun getProductById(id: Long): Product? {
        val product: Optional<Product> = productRepository.findById(id)
        return product.orElse(null)
    }

    fun createProduct(received: Product): Any {
        // TODO - implement duplicate search when more data classes will be made
        val saved = productRepository.save(received)
        return saved
    }

    //...

    fun getDetailsForProduct(id: Long): ProductDetails? {
        val product = this.getProductById(id)
        if (product != null) {
            return product.productDetails
        }
        return null
    }

    fun createProductDetails(received: ProductDetails): ProductDetails? {
        if (received.product != null) {
            val searched = productDetailsRepository.findByProduct(received.product)
            if (searched != null) {
                val saved = productDetailsRepository.save(received)
                return saved
            }
        }
        return null
    }

}