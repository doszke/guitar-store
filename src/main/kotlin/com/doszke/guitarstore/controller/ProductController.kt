package com.doszke.guitarstore.controller

import com.doszke.guitarstore.model.Product
import com.doszke.guitarstore.model.ProductDetails
import com.doszke.guitarstore.service.ProductService
import org.apache.coyote.Response
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException.BadRequest

@RestController
class ProductController(
        val productService: ProductService
) {


    @GetMapping(path = ["/api/products/{id}"])
    fun getProductById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val obj = productService.getProductById(id)
        if (obj==null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/products"])
    fun createProduct(@RequestBody product: Product): ResponseEntity<Any> {
        val obj = productService.createProduct(product)
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }




    //...

    @GetMapping(path = ["/api/products/{id}/details"])
    fun getProductDetailsFOrProductById(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        val obj = productService.getDetailsForProduct(id)
        if (obj==null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(obj)
    }

    @PostMapping(path = ["/api/products/details"])
    fun createProductDetails(@RequestBody productDetails: ProductDetails): ResponseEntity<Any> {
        val obj = productService.createProductDetails(productDetails)
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}