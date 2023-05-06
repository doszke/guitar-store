package com.doszke.guitarstore.repository

import com.doszke.guitarstore.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, Long>