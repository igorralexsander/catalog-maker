package com.igorralexsander.domain.merchantpage.model

import java.util.UUID

data class Store(
    val id:UUID,
    val name:String,
    val baseUrl:String,
    val timeout:Int,
)