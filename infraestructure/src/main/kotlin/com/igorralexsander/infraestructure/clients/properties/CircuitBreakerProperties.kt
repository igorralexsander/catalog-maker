package com.igorralexsander.infraestructure.clients.properties

data class CircuitBreakerProperties(
    val failureRateThreshold: Float,
    val waitDurationInOpenStateInSeconds: Long,
    val minimumNumberOfCalls: Int
)