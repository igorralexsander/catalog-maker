package com.igorralexsander

import com.igorralexsander.configuration.configurationModuleConfig
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.logging.LogManager
import org.kodein.di.Kodein

fun main() {
    try{
        val kodein = Kodein {
            import(configurationModuleConfig)
        }
        embeddedServer(
            Netty,
            applicationEngineEnvironment {
                val hoconConfig = ConfigFactory.load().resolve()
                config = HoconApplicationConfig(hoconConfig)
                val serverPort = config.propertyOrNull("ktor.deployment.port")?.getString()?.toInt() ?: 8080

                module {
                    catalogMaker(kodein)
                }

                connector {
                    host = "0.0.0.0"
                    port = serverPort
                }
            }

        ).start(wait = true)

    }catch (t: Throwable){
        log.error(t.message)
    }
}

val log: Logger = LoggerFactory.getLogger("com.igorralexsander.Application")