package com.igorralexsander.routes

import com.igorralexsander.domain.merchantpage.MerchantPageUseCase
import com.igorralexsander.domain.merchantpage.model.Store
import com.igorralexsander.domain.merchantpage.model.UseCaseInput
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.instance
import java.util.*

fun Routing.productPage(kodein: Kodein){
    val useCase:MerchantPageUseCase = kodein.direct.instance()
    post("/productpage/v1") {
        val body = call.receive<ProductPagePayload>()
        val useCaseInput = UseCaseInput(
            body.requestId,
            body.merchantId, body.page,
            body.store
        )
        val result = useCase.RunUseCase(useCaseInput)

        call.respond(result)
    }
}

data class ProductPagePayload(
    val requestId: UUID,
    val merchantId:String,
    val page:Int,
    val store: Store
)