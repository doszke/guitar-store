package com.doszke.guitarstore.mapper

import com.doszke.guitarstore.model.ProductCategory
import com.doszke.guitarstore.model.dto.ProductCategoryDTO
import org.mapstruct.Mapper

@Mapper
interface ProductCategoryMapper {

    fun dtoToModel(dto: ProductCategoryDTO): ProductCategory

    fun modelToDto(model: ProductCategory): ProductCategoryDTO
}