package com.doszke.guitarstore.controller

import com.doszke.guitarstore.model.Product
import jakarta.transaction.TransactionScoped
import jakarta.transaction.Transactional
import junit.framework.TestCase.*
import org.junit.BeforeClass
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ProductControllerTest {

    companion object {


        val idsOfObjectsNotPresentInDatabase = listOf<Long>(-1L, -2L, -3L)

        @JvmStatic
        @BeforeClass
        fun initializeTestDatabase(): Unit {

        }
    }

    var restTemplate = TestRestTemplate()
    @Test
    fun getProductByIdTest() {
        for (id in idsOfObjectsNotPresentInDatabase) {
            val responseEntity = restTemplate.exchange(
                    "Http://localhost:8080/api/products/%d".format(id),
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<Product>() {}
            )
            assertEquals(HttpStatus.NOT_FOUND, responseEntity.statusCode)
            assertNotNull(responseEntity.body)
            print(responseEntity.body)
        }
    }
}

