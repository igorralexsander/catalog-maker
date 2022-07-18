package com.igorralexsander.domain.merchantpage

import com.igorralexsander.domain.merchantpage.model.UseCaseInput

class MerchantPageUseCase(
    val websiteClient:WebsiteClient
) {
    suspend fun RunUseCase(input: UseCaseInput): String {
       return websiteClient.FetchData(input.merchantId, input.page, input.store)
    }
}