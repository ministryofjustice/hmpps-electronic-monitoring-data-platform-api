package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.classes.User
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.tables.Users


fun Routing.home() {
  get("/") {
    call.respondText("Hello World!")
  }
}

//  route("/user") {
//    get("/") {
////    call.respondText("Hi!")
//      val users = transaction {
//        Users.selectAll().map { Users.toUser(it) }
//      }
//      call.respond(users)
//    }
//    post("/") {
//      val user = call.receive<User>()
//      transaction {
//        Users.insert {
//          it[Users.name] = user.name
//          it[Users.age] = user.age
//        }
//      }
//      call.respond(user)
//    }
//    get("/{id}") {
//      val id = call.parameters["id"]!!.toInt()
//      val users = transaction {
//        Users.select { Users.id eq id }.map { Users.toUser(it) }
//      }
//      call.respond(users)
//    }
//  }



