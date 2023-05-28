package com.doszke.guitarstore.service

import com.doszke.guitarstore.exception.BadRequestException
import com.doszke.guitarstore.exception.NotFoundException
import com.doszke.guitarstore.model.factory.ProductFactory
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
        val productCategoryMapper: ProductCategoryMapper,
        val productFactory: ProductFactory
) {

    fun getProductById(id: Long): Product? {
        return productRepository.findById(id).orElseThrow { NotFoundException() }
    }

    fun createProduct(received: ProductDTO): Product? {
        val mapped = productMapper.dtoToModel(received)
        if (received.productCategoryId != null) {
            val category = productCategoryRepository.findById(received.productCategoryId).orElseThrow { BadRequestException() }
            if (!category.products.contains(mapped)) {
                mapped.productCategory = category
                category.products + mapped

                val saved = productRepository.save(mapped)
                productCategoryRepository.save(category)
                return saved
            }
        }
        throw BadRequestException()
    }


    fun getDetailsForProduct(id: Long): ProductDetails? {
        val product = this.getProductById(id)
        if (product?.productDetails == null) {
            throw NotFoundException()
        }
        return product.productDetails
    }

    fun createProductDetails(received: ProductDetailsDTO, productId: Long): ProductDetails? {
        val product = this.getProductById(productId) ?: throw NotFoundException()
        if (product.productDetails != null) {
            throw BadRequestException()
        }
        val mapped = productDetailsMapper.dtoToModel(received)
        mapped.product = product
        product.productDetails = mapped

        val savedD = productDetailsRepository.save(mapped)
        productRepository.save(product)

        return savedD
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
                return productCategoryRepository.save(mapped)
            }
        }
        throw BadRequestException()
    }

    fun getAllProducts(): List<Product> {
        return productRepository.findAll().toList()
    }

    fun getAllCategories(): List<ProductCategory> {
        return productCategoryRepository.findAll().toList()
    }

    fun getProductsForCategory(id: Long): List<Product>? {
        val searched = productCategoryRepository.findById(id).orElseThrow{ NotFoundException() }
        if (searched != null) {
            return searched.products
        }
        throw BadRequestException()
    }

    fun modifyProductDetails(productDetailsDTO: ProductDetailsDTO, productId: Long): Any? {
        val product: Product = productRepository.findById(productId).orElseThrow{ NotFoundException() }
        if (productId == product.id) {
            val details: ProductDetails? = product.productDetails
            if (details != null) {
                val newDetails = productDetailsMapper.dtoToModel(productDetailsDTO)
                val modifiedDetails = productFactory.modifyDetails(details, newDetails)
                productDetailsRepository.save(modifiedDetails)
                product.productDetails = modifiedDetails
                productRepository.save(product)
                return modifiedDetails
            }
        }
        throw BadRequestException()
    }
}