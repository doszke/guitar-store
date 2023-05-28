package com.doszke.guitarstore.controller

import com.doszke.guitarstore.model.dto.ProductCategoryDTO
import com.doszke.guitarstore.model.dto.ProductDTO
import com.doszke.guitarstore.model.dto.ProductDetailsDTO
import com.doszke.guitarstore.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
        val productService: ProductService
) {

    // Product methods

    @GetMapping(path = ["/api/products"])
    fun getAllProducts(): ResponseEntity<Any> {
        val obj = productService.getAllProducts()
        return ResponseEntity.ok(obj)
    }

    @GetMapping(path = ["/api/products/{id}"])
    fun getProductById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val obj = productService.getProductById(id)
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/products"])
    fun createProduct(@RequestBody product: ProductDTO): ResponseEntity<Any> {
        val obj = productService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }


    //ProductDetails methods

    @GetMapping(path = ["/api/products/{id}/details"])
    fun getProductDetailsForProductById(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.getDetailsForProduct(id)
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/products/{id}/details"])
    fun createProductDetails(@RequestBody productDetails: ProductDetailsDTO, @PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.createProductDetails(productDetails, id)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping(path = ["/api/products/{id}/details"])
    fun modifyProductDetails(@RequestBody productDetails: ProductDetailsDTO, @PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.modifyProductDetails(productDetails, id)
        return ResponseEntity.ok(obj)
    }


    // ProductCategory methods

    @GetMapping(path = ["/api/categories"])
    fun getAllCategories(): ResponseEntity<Any> {
        val obj = productService.getAllCategories()
        return ResponseEntity.ok(obj)
    }

    @GetMapping(path = ["/api/categories/{id}/products"])
    fun getAllProductsForCategory(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.getProductsForCategory(id)
        return ResponseEntity.ok(obj)
    }

    @GetMapping(path = ["/api/products/{id}/category"])
    fun getProductCategoryForProductById(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.getCategoryForProduct(id)
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/categories"])
    fun createCategory(@RequestBody productCategory: ProductCategoryDTO): ResponseEntity<Any> {
        print(productCategory.name)
        val obj = productService.createProductCategory(productCategory)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}