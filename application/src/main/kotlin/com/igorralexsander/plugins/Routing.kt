package com.igorralexsander.plugins

import com.igorralexsander.routes.mainRoute
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        mainRoute()
    }
}
