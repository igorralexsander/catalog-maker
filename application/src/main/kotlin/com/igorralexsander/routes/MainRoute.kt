package com.igorralexsander.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.mainRoute(){
    get {
        call.respondText(status = HttpStatusCode.OK, text = "Hello World!")
    }
}