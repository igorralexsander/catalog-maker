package com.igorralexsander.configuration

import com.igorralexsander.domain.merchantpage.MerchantPageUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val useCasesConfigurationModule = Kodein.Module("useCasesModule") {
    import(clientsConfigurationModule)
    import(merchantUseCaseModule)
}

val merchantUseCaseModule = Kodein.Module("merchantUseCaseModule") {
    bind() from singleton { MerchantPageUseCase(instance()) }
}