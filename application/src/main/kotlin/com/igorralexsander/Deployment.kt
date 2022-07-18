package com.igorralexsander

import com.fasterxml.jackson.databind.SerializationFeature
import com.igorralexsander.configuration.useCasesConfigurationModule
import com.igorralexsander.routes.productPage
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

fun Application.catalogMaker(
    refKodein: Kodein,
    modules: List<Kodein.Module> = listOf(useCasesConfigurationModule)
):Kodein {
    return Kodein{
        extend(refKodein, copy = Copy.All)
        bind() from instance(environment.config)
        modules.forEach {
            import(it)
        }

        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }

        onReady {
            routing {
                productPage(kodein)
            }
        }
    }
}