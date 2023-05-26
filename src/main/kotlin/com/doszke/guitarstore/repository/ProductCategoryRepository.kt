package com.doszke.guitarstore.repository

import com.doszke.guitarstore.model.ProductCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProductCategoryRepository: CrudRepository<ProductCategory, Long> {
    fun findByName(name: String): Optional<ProductCategory>

}