package com.doszke.guitarstore.model

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
        @OneToOne val productDetails: ProductDetails?,
        @ManyToOne(fetch = FetchType.LAZY) val productCategory: ProductCategory?
) {
    @Deprecated("Hiberante-use only")
    constructor() :this(null, null, null)
}
@Entity
@Table(name = "product_details")
data class ProductDetails(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
        val description: String,
        val price: Double,
        @OneToOne(mappedBy = "productDetails", cascade = [CascadeType.ALL]) val product: Product?
) {
    @Deprecated("Hiberante-use only")
    constructor() :this(null, "", .0, null)
}
@Entity
@Table(name = "product_categories")
data class ProductCategory(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)  val id: Long?,
        val name: String,
        @OneToMany(mappedBy = "productCategory") val products: List<Product>
) {
    @Deprecated("Hiberante-use only")
    constructor() : this(null, "", emptyList())
}

@Entity
data class Test(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)  val id: Long?,
        @Column val name: String
) {
    @Deprecated("Hiberante-use only")
    constructor(): this(null, "")
}