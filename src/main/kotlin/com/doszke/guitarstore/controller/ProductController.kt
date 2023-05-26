package com.doszke.guitarstore.controller

import com.doszke.guitarstore.model.dto.ProductCategoryDTO
import com.doszke.guitarstore.model.dto.ProductDTO
import com.doszke.guitarstore.model.dto.ProductDetailsDTO
import com.doszke.guitarstore.service.ProductService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
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
        if (obj == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/products"])
    fun createProduct(@RequestBody product: ProductDTO): ResponseEntity<Any> {
        // TODO: Pass details and catgeory IDs when appropriate methods implemented
        val obj = productService.createProduct(product)
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }


    //ProductDetails methods

    @GetMapping(path = ["/api/products/{id}/details"])
    fun getProductDetailsForProductById(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.getDetailsForProduct(id)
        if (obj == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/products/{id}/details"])
    fun createProductDetails(@RequestBody productDetails: ProductDetailsDTO, @PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.createProductDetails(productDetails, id)
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping(path = ["/api/products/{id}/details"])
    fun modifyProductDetails(@RequestBody productDetails: ProductDetailsDTO, @PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        return try {
            val obj = productService.modifyProductDetails(productDetails, id)
            ResponseEntity.ok(obj)
        } catch (ex: NotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (ex: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
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
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.ok(obj)
    }

    @GetMapping(path = ["/api/products/{id}/category"])
    fun getProductCategoryForProductById(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.getCategoryForProduct(id)
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/categories"])
    fun createCategory(@RequestBody productCategory: ProductCategoryDTO): ResponseEntity<Any> {
        print(productCategory.name)
        val obj = productService.createProductCategory(productCategory)
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}