package com.example.pages

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


  fun Route.hellowWorld(){
    get("/") {
      call.respondText("Hello World!!")
    }
  }
