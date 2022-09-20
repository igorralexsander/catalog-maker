package com.igorralexsander.infraestructure.clients.properties

data class HttpClientProperties(
    val baseUrl:String,
    val maxConnectionsCount:Int,
    val maxConnectionsPerRoute:Int,
    val keepAliveTime:Long=5000,
    val connectTimeout:Long=300,
    val readTimeout:Long=1000,
    val connectRetryAttempts:Int,
    val pipelineMaxSize:Int=10000,
    val resilienceProperties: ResilienceProperties
)