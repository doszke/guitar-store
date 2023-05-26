package com.doszke.guitarstore.model

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
        val name: String?,
        @OneToOne var productDetails: ProductDetails?,
        @ManyToOne(fetch = FetchType.LAZY) var productCategory: ProductCategory?
) {
    @Deprecated("Hiberante-use only")
    constructor() :this(null, null, null, null)
}
@Entity
@Table(name = "product_details")
data class ProductDetails(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
        val description: String?,
        val price: Double?,
        @OneToOne(mappedBy = "productDetails", cascade = [CascadeType.ALL]) var product: Product?
) {
    @Deprecated("Hiberante-use only")
    constructor() :this(null, "", .0, null)
}
@Entity
@Table(name = "product_categories")
data class ProductCategory(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)  val id: Long?,
        val name: String?,
        @OneToMany(mappedBy = "productCategory") var products: MutableList<Product>
) {
    @Deprecated("Hiberante-use only")
    constructor() : this(null, "", mutableListOf())
}

