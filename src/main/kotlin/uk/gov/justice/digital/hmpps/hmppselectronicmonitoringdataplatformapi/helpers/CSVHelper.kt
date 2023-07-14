package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import java.io.ByteArrayInputStream
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.PrintWriter

class CSVHelper {
  fun locationsToCSV(locations: List<Location>): ByteArrayInputStream {
    val format = CSVFormat.DEFAULT.withHeader("Id", "Latitude", "Longitude",
      "LocationTime")
    try {
      ByteArrayOutputStream().use { out ->
        CSVPrinter(
          PrintWriter(out),
          format
        ).use { csvPrinter ->
          for (l in locations) {
            val data: List<String?> = listOf(
              l.id.toString(),
              l.latitude.toString(),
              l.longitude.toString(),
              l.locationTime.toString()
            )
            csvPrinter.printRecord(data)
          }
          csvPrinter.flush()
          return ByteArrayInputStream(out.toByteArray())
        }
      }
    } catch (e: IOException) {
      throw RuntimeException(
        "fail to import data to CSV file: "
          + e.message
      )
    }
  }
}