package com.igorralexsander.configuration

import com.igorralexsander.infraestructure.clients.B2WClient
import com.igorralexsander.infraestructure.clients.B2WResilience
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val clientsConfigurationModule = Kodein.Module("clientsModule") {
    import(b2wClientModule)
}

val b2wClientModule = Kodein.Module("b2wClientModule") {
    bind() from singleton { B2WClient(instance()) }
}

val b2wResilience = Kodein.Module("b2wResilience") {
    bind() from singleton { B2WResilience(, ) }
}

