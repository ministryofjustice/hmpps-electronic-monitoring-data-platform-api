package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.routes.users

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.classes.User
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.tables.UsersTable

fun Routing.users() {
  route("/user") {
    get("/") {
      val users = transaction {
        UsersTable.selectAll().map { UsersTable.toUser(it) }
      }
      call.respond(users)
    }
    post("/") {
      val user = call.receive<User>()
      transaction {
        UsersTable.insert {
          it[UsersTable.name] = user.name
          it[UsersTable.age] = user.age
        }
      }
      call.respond(user)
    }
    get("/{id}") {
      val id = call.parameters["id"]!!.toInt()
      val users = transaction {
        UsersTable.select { UsersTable.id eq id }.map { UsersTable.toUser(it) }
      }
      call.respond(users)
    }
  }
}
