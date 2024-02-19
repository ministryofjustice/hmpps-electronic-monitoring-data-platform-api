package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

// import software.amazon.awssdk.services.athena.*
// import software.amazon.awssdk.*

// import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.athena.*
import java.sql.ResultSet
import java.util.*
import com.amazon.athena.jdbc.AthenaDriver
import software.amazon.awssdk.services.athena.AthenaClient
// import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
// import software.amazon.awssdk.regions.Region
// import software.amazon.awssdk.services.athena.AthenaClient
import java.sql.Connection
import java.sql.Statement

class AthenaServiceRepository : ServiceRepository {
  private val connectionParameters: Properties = Properties()
  private var url: String = "jdbc:athena://" //  private var athenaUrl = "jdbc:athena://WorkGroup=primary;Region=us-east-1;...;"
  private var athenaConnection: Connection?

//  private val builder = AthenaClient.builder()
//    .region(Region.US_WEST_2)
//    .credentialsProvider(ProfileCredentialsProvider.create())
//
//  fun createClient(): AthenaClient? {
//    return builder.build()
//  }

  init {
    connectionParameters.setProperty("Workgroup", "primary")
    connectionParameters.setProperty("Region", "us-east-2")
    connectionParameters.setProperty("Catalog", "AwsDataCatalog")
    connectionParameters.setProperty("Database", "sampledatabase")
    connectionParameters.setProperty("OutputLocation", "s3://samplebucket")
    connectionParameters.setProperty("CredentialsProvider", "DefaultChain")

    var athenaDriver: AthenaDriver = AthenaDriver()
    athenaConnection = athenaDriver.connect(url, connectionParameters)

    //  Will use AwsCredentialsProvider from new AWS Java sdk:
    //  https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/auth/credentials/AwsCredentialsProvider.html
  }

  override fun getData(fieldList: String, criteria: String): ResultSet? {
    return runDefaultQuery()
  }

  //  @Throws(SQLException::class)
  override fun runDefaultQuery(): ResultSet? {
    if (athenaConnection != null) {
      val statement: Statement = athenaConnection!!.createStatement()

      val query = "SELECT * FROM silver_visit_fct LIMIT 7"
      return statement.executeQuery(query)
    }
    return null
  }
}
