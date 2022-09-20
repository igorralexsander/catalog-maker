package com.igorralexsander.domain.merchantpage

import com.igorralexsander.domain.merchantpage.model.Product
import com.igorralexsander.domain.merchantpage.model.Store

interface WebsiteClient {
    suspend fun FetchData(merchantId:String, page:Int, store:Store):List<Product>
}