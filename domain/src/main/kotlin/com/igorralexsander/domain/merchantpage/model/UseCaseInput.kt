package com.igorralexsander.domain.merchantpage.model

import com.igorralexsander.domain.merchantpage.model.Store
import java.util.UUID

data class UseCaseInput(
    val requestId: UUID,
    val merchantId:String,
    val page:Int,
    val store:Store
)