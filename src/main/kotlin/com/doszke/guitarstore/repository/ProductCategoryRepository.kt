package com.doszke.guitarstore.repository

import com.doszke.guitarstore.model.Test
import org.springframework.data.repository.CrudRepository

interface TestRepository: CrudRepository<Test, Long>