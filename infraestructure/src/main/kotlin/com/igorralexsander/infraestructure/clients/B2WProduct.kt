package com.igorralexsander.infraestructure.clients

import com.igorralexsander.domain.merchantpage.model.Product

data class B2WProduct(
    val id:String,
    val name:String
){
    fun toDomainProduct():Product{
        return Product(id, name)
    }
}