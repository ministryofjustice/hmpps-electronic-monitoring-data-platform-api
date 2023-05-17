package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import com.apurebase.kgraphql.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.inject
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.services.DatabaseService
import java.sql.*

fun Application.configureDatabases() : Int {
  val databaseService: DatabaseService by inject()
  System.getenv()
  //h2 connection
  Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1", "org.h2.Driver")
  //  Connect to database on the docker container
  //  Database.connect("jdbc:postgresql://localhost:5432/",driver = "org.postgresql.Driver",user = "postgres", password = "root")

  //docker connection
//  Database.connect(
//    System.getenv("EM_DATABASE_CONNECTION"),
//    driver = "org.postgresql.Driver",
//    user = "postgres",
//    password = "root",
//  )
//  Database.connect("jdbc:postgresql://database:5432/",driver = "org.postgresql.Driver",user = "postgres", password = "root")
  databaseService.initializeDatabase();
  return 1;
}

//  val schema = KGraphQL.schema {
//    query("heroes") {
//      resolver { ->
//        transaction {
//          Users.selectAll().map { Users.toUser(it) }
//        }
//      }
//    }
//  }
//}

/**
 * Makes a connection to a Postgres database.
 *
 * In order to connect to your running Postgres process,
 * please specify the following parameters in your configuration file:
 * - postgres.url -- Url of your running database process.
 * - postgres.user -- Username for database connection
 * - postgres.password -- Password for database connection
 *
 * If you don't have a database process running yet, you may need to [download]((https://www.postgresql.org/download/))
 * and install Postgres and follow the instructions [here](https://postgresapp.com/).
 * Then, you would be able to edit your url,  which is usually "jdbc:postgresql://host:port/database", as well as
 * user and password values.
 *
 *
 * @param embedded -- if [true] defaults to an embedded database for tests that runs locally in the same process.
 * In this case you don't have to provide any parameters in configuration file, and you don't have to run a process.
 *
 * @return [Connection] that represent connection to the database. Please, don't forget to close this connection when
 * your application shuts down by calling [Connection.close]
 * */
fun Application.connectToPostgres(embedded: Boolean): Connection {
  Class.forName("org.postgresql.Driver")
  if (embedded) {
    return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "")
  } else {
    val url = environment.config.property("postgres.url").getString()
    val user = environment.config.property("postgres.user").getString()
    val password = environment.config.property("postgres.password").getString()

    return DriverManager.getConnection(url, user, password)
  }
}
