package com.doszke.guitarstore.service

import com.doszke.guitarstore.mapper.ProductCategoryMapper
import com.doszke.guitarstore.mapper.ProductDetailsMapper
import com.doszke.guitarstore.mapper.ProductMapper
import com.doszke.guitarstore.model.Product
import com.doszke.guitarstore.model.ProductCategory
import com.doszke.guitarstore.model.ProductDetails
import com.doszke.guitarstore.model.dto.ProductCategoryDTO
import com.doszke.guitarstore.model.dto.ProductDTO
import com.doszke.guitarstore.model.dto.ProductDetailsDTO
import com.doszke.guitarstore.repository.ProductCategoryRepository
import com.doszke.guitarstore.repository.ProductDetailsRepository
import com.doszke.guitarstore.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
        val productRepository: ProductRepository,
        val productDetailsRepository: ProductDetailsRepository,
        val productCategoryRepository: ProductCategoryRepository,
        val productMapper: ProductMapper,
        val productDetailsMapper: ProductDetailsMapper,
        val productCategoryMapper: ProductCategoryMapper
) {

    fun getProductById(id: Long): Product? {
        val product = productRepository.findById(id)
        return product.orElse(null)
    }

    fun createProduct(received: ProductDTO): Product? {
        val mapped = productMapper.dtoToModel(received)
        if (received.productCategoryId != null) {
            val category = productCategoryRepository.findById(received.productCategoryId).orElse(null)
            if (category != null) {
                if (!category.products.contains(mapped)) {
                    mapped.productCategory = category
                    category.products + mapped

                    val saved = productRepository.save(mapped)
                    productCategoryRepository.save(category)
                    return saved
                }
            }
        }
        return null
    }

    //...

    fun getDetailsForProduct(id: Long): ProductDetails? {
        val product = this.getProductById(id)
        return product?.productDetails
    }

    fun createProductDetails(received: ProductDetailsDTO, productId: Long): ProductDetails? {
        val product = this.getProductById(productId)

        if (product != null) {
            if (product.productDetails == null) {
                val mapped = productDetailsMapper.dtoToModel(received)
                mapped.product = product
                product.productDetails = mapped

                val savedD = productDetailsRepository.save(mapped)
                productRepository.save(product)

                return savedD
            }
        }

        return null
    }

    fun getCategoryForProduct(id: Long): ProductCategory? {
        val product = this.getProductById(id)
        return product?.productCategory
    }

    fun createProductCategory(received: ProductCategoryDTO): ProductCategory? {
        if (received.name != null) {
            val searched = productCategoryRepository.findByName(received.name).orElse(null)
            if (searched == null) {
                val mapped = productCategoryMapper.dtoToModel(received)
                val saved = productCategoryRepository.save(mapped)
                return saved
            }
        }
        return null
    }

    fun getAllProducts(): List<Product> {
        return productRepository.findAll().toList()
    }

    fun getAllCategories(): List<ProductCategory> {
        return productCategoryRepository.findAll().toList()
    }

    fun getProductsForCategory(id: Long): List<Product>? {
        val searched = productCategoryRepository.findById(id).orElse(null)
        if (searched != null) {
            return searched.products
        }
        return null
    }

    fun modifyProductDetails(productDetailsDTO: ProductDetailsDTO, productId: Long): Any? {
        val product: Product? = productRepository.findById(productId).orElse(null)
        if (product != null) {

        }
        return null
    }

    fun productExists(id: Long): Boolean {
        return !productRepository.findById(id).isEmpty
    }

    fun productHasDetails(id: Long): Boolean {
        // to be used after productExists
        val product: Product = productRepository.findById(id).orElse(null)
        return product.productDetails != null
    }

}