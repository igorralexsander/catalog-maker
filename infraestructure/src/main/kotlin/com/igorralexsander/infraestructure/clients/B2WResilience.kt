package com.igorralexsander.infraestructure.clients

import com.igorralexsander.infraestructure.clients.properties.CircuitBreakerProperties
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import java.time.Duration

class B2WResilience(
    circuitBreakerRegistry: CircuitBreakerRegistry,
    circuitBreakerProperties: CircuitBreakerProperties
) {
    val circuitBreaker:CircuitBreaker = circuitBreakerRegistry.circuitBreaker(
         "b2w-client",
        CircuitBreakerConfig.custom()
            .failureRateThreshold(circuitBreakerProperties.failureRateThreshold)
            .waitDurationInOpenState(Duration.ofSeconds(circuitBreakerProperties.waitDurationInOpenStateInSeconds))
            .slidingWindow(
                10,
                10,
                CircuitBreakerConfig.SlidingWindowType.COUNT_BASED
            ).build()
    )

}