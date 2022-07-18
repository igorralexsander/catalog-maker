package com.igorralexsander.infraestructure.clients

import com.igorralexsander.domain.merchantpage.WebsiteClient
import com.igorralexsander.domain.merchantpage.model.Store
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class B2WClient : WebsiteClient {
    override suspend fun FetchData(merchantId: String, page: Int, store: Store): String {
        val client = HttpClient(CIO)
        val response: HttpResponse = client.get("${store.baseUrl}/lojista/$merchantId")
        return response.body<String>().toString()
    }
}