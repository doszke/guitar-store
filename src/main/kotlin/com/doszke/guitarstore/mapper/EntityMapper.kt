package com.doszke.guitarstore.mapper


interface GenericMapper<DTO, DAO> {

    fun dtoToModel(dto: DTO): DAO

    fun modelToDto(model: DAO): DTO
}
