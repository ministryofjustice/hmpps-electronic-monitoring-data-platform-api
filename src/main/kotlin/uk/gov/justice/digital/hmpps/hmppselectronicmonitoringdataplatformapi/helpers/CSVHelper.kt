package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.IConvertable
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.PrintWriter

class CSVHelper {

  fun convertToCSV(dataRows: List<IConvertable>): ByteArrayInputStream {
    try {
      val convertedData = dataRows.map { row ->
        row.getProperties()
      }.map {
        it.map { pair -> pair.second }
      }

      val fieldNames = dataRows[0].getProperties().map { it.first }.toTypedArray()

      ByteArrayOutputStream().use { out ->
        CSVPrinter(
          PrintWriter(out),
          CSVFormat.DEFAULT.withHeader(*fieldNames),
        ).use { csvPrinter ->
          for (row in convertedData) {
            csvPrinter.printRecord(row)
          }
          csvPrinter.flush()
          return ByteArrayInputStream(out.toByteArray())
        }
      }
    } catch (e: IOException) {
      throw RuntimeException("fail to import data to CSV file: " + e.message)
    }
  }
}
