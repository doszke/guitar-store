package com.doszke.guitarstore.mapper

import com.doszke.guitarstore.model.Product
import com.doszke.guitarstore.model.ProductCategory
import com.doszke.guitarstore.model.ProductDetails
import com.doszke.guitarstore.model.dto.ProductCategoryDTO
import com.doszke.guitarstore.model.dto.ProductDTO
import com.doszke.guitarstore.model.dto.ProductDetailsDTO
import org.springframework.stereotype.Component

@Component
class ProductMapper(
        val productDetailsMapper: ProductDetailsMapper
): GenericMapper<ProductDTO, Product> {
    override fun dtoToModel(dto: ProductDTO): Product {
        return Product(dto.id, dto.name, null, null)
    }

    override fun modelToDto(model: Product): ProductDTO {
        return ProductDTO(
                model.id,
                model.name,
                if (model.productDetails != null) productDetailsMapper.modelToDto(model.productDetails!!) else null,
                model.productCategory?.id)
    }

}

@Component
class ProductDetailsMapper: GenericMapper<ProductDetailsDTO, ProductDetails> {
    override fun dtoToModel(dto: ProductDetailsDTO): ProductDetails {
        return ProductDetails(dto.id, dto.description, dto.price, null)
    }

    override fun modelToDto(model: ProductDetails): ProductDetailsDTO {
        return ProductDetailsDTO(model.id, model.description, model.price, model.product?.id)
    }

}

@Component
class ProductCategoryMapper(
        private val productMapper: ProductMapper
): GenericMapper<ProductCategoryDTO, ProductCategory> {
    override fun dtoToModel(dto: ProductCategoryDTO): ProductCategory {
        return ProductCategory(dto.id, dto.name, mutableListOf())
    }

    override fun modelToDto(model: ProductCategory): ProductCategoryDTO {
        return ProductCategoryDTO(model.id, model.name, model.products.map{ productMapper.modelToDto(it) })
    }

}