package com.igorralexsander.configuration

import com.igorralexsander.common.ApplicationConfigExtensions.getFloat
import com.igorralexsander.common.ApplicationConfigExtensions.getInt
import com.igorralexsander.common.ApplicationConfigExtensions.getLong
import com.igorralexsander.common.ApplicationConfigExtensions.getString
import com.igorralexsander.infraestructure.clients.properties.B2WClientProperties
import com.igorralexsander.infraestructure.clients.properties.CircuitBreakerProperties
import com.igorralexsander.infraestructure.clients.properties.HttpClientProperties
import com.igorralexsander.infraestructure.clients.properties.ResilienceProperties
import io.ktor.server.config.*
import org.kodein.di.Kodein
import org.kodein.di.bindings.Singleton
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val configurationModuleConfig = Kodein.Module("configurationModuleConfig"){
    bind() from singleton { b2wClientPropertiesConfiguration(instance()) }
}

fun b2wClientPropertiesConfiguration(config:ApplicationConfig):B2WClientProperties {
    return B2WClientProperties(
        HttpClientProperties(
            baseUrl = config.getString("b2wClient.url"),
            maxConnectionsCount = config.getInt("b2wClient.maxConnectionsCount"),
            maxConnectionsPerRoute = config.getInt("b2wClient.maxConnectionsPerRoute"),
            keepAliveTime = config.getLong("b2wClient.keepAliveTime"),
            connectTimeout = config.getLong("b2wClient.connectTimeout"),
            readTimeout = config.getLong("b2wClient.readTimeout"),
            connectRetryAttempts = config.getInt("b2wClient.connectRetryAttempts"),
            pipelineMaxSize = config.getInt("b2wClient.pipelineMaxSize"),
            resilienceProperties = ResilienceProperties(
                circuitBreakerProperties = CircuitBreakerProperties(
                    failureRateThreshold = config.getFloat("b2wClient.resilience.failureRateThreshold"),
                    waitDurationInOpenStateInSeconds = config.getLong("b2wClient.resilience.waitDurationInOpenStateInSeconds"),
                    minimumNumberOfCalls = config.getInt("b2wClient.resilience.minimumNumberOfCalls")
                )
            )
        )
    )
}

