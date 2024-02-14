package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import java.sql.ResultSet
import java.util.*


class AthenaServiceRepository : ServiceRepository {
  private val connectionParameters: Properties = Properties()
  private var url: String

  //  private var athenaUrl = "jdbc:athena://WorkGroup=primary;Region=us-east-1;...;"
  private var athenaConnection: Connection = Connection()

  init {
    url = "jdbc:athena://"
    connectionParameters.setProperty("Workgroup", "primary")
    connectionParameters.setProperty("Region", "us-east-2")
    connectionParameters.setProperty("Catalog", "AwsDataCatalog")
    connectionParameters.setProperty("Database", "sampledatabase")
    connectionParameters.setProperty("OutputLocation", "s3://samplebucket")
    connectionParameters.setProperty("CredentialsProvider", "DefaultChain")

    var athenaDriver: AthenaDriver = AthenaDriver()
    athenaConnection = driver.connect(url, connectionParameters)

    //  Will use AwsCredentialsProvider from new AWS Java sdk:
    //  https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/auth/credentials/AwsCredentialsProvider.html
  }

  override fun getData(fieldList: String, criteria: String): ResultSet? {
    return runDefaultQuery()
  }

  //  @Throws(SQLException::class)
  override fun runDefaultQuery(): ResultSet? {
    val statement: Statement = connection.createStatement()

    val query = "SELECT * FROM silver_visit_fct LIMIT 7"
    return statement.executeQuery(query)
  }
}
