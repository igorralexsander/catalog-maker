package com.igorralexsander.infraestructure.clients

import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.core.util.JacksonFeature
import com.igorralexsander.domain.merchantpage.WebsiteClient
import com.igorralexsander.domain.merchantpage.model.Product
import com.igorralexsander.domain.merchantpage.model.Store
import com.igorralexsander.infraestructure.clients.properties.B2WClientProperties
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.kotlin.circuitbreaker.executeSuspendFunction
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import io.ktor.http.*
import io.ktor.serialization.jackson.*

class B2WClient(
    private val properties: B2WClientProperties,
    private val circuitBreaker:CircuitBreaker
    ) : WebsiteClient {
    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation){
            jackson()
        }
        engine {
            maxConnectionsCount = properties.httpClientProperties.maxConnectionsCount
            endpoint.apply {
                connectAttempts = properties.httpClientProperties.connectRetryAttempts
                maxConnectionsPerRoute = properties.httpClientProperties.maxConnectionsPerRoute
                pipelineMaxSize = properties.httpClientProperties.pipelineMaxSize
                keepAliveTime = properties.httpClientProperties.keepAliveTime
                connectTimeout = properties.httpClientProperties.connectTimeout
                requestTimeout = properties.httpClientProperties.readTimeout
            }
        }
    }

    override suspend fun FetchData(merchantId: String, page: Int, store: Store): List<Product> {
        return circuitBreaker.executeSuspendFunction {
            val response: HttpResponse = client.get("${store.baseUrl}/lojista/$merchantId")
            val result = B2WPageMapper().mapToDomain(response.body<String>().toString())
            return@executeSuspendFunction result.map { it.toDomainProduct() }
        }
    }
}