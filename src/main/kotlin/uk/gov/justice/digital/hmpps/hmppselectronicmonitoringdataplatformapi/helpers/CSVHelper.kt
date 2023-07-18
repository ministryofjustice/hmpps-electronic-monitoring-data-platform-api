package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.IConvertable
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.PrintWriter

class CSVHelper {
  fun locationsToCSV(locations: List<Location>): ByteArrayInputStream {
    val format = CSVFormat.DEFAULT.withHeader("Id", "Latitude", "Longitude", "LocationTime")
    val rows: MutableList<List<Pair<String, String>>> = mutableListOf()
    for (l in locations) {

      val data: MutableList<Pair<String, String>> = mutableListOf()
      for (prop in l.getProperties()) {
        data.add(prop)
      }
      // val properties = l.getProperties()
      rows.add(data)
    }
    return Convert2(format, locations)
  }

  fun Convert(format: CSVFormat, rows: MutableList<List<String?>>): ByteArrayInputStream {
    try {
      ByteArrayOutputStream().use { out ->
        CSVPrinter(
          PrintWriter(out),
          format,
        ).use { csvPrinter ->
          for (row in rows) {

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

  fun Convert2(format: CSVFormat, dataRows: List<IConvertable>): ByteArrayInputStream {
    try {
      val rows: MutableList<List<String?>> = mutableListOf()
      for (l in dataRows) {

        val convertedData: MutableList<String?> = mutableListOf()
        for (prop in l.getProperties()) {
          convertedData.add(prop.second)
        }
        rows.add(convertedData)
      }
      ByteArrayOutputStream().use { out ->
        CSVPrinter(
          PrintWriter(out),
          format,
        ).use { csvPrinter ->
          for (row in rows) {

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

 // fun LocationAggregationsToCSV(locations: List<LocationAggregation>): ByteArrayInputStream {
//    val format = CSVFormat.DEFAULT.withHeader("Latitude", "Longitude", "DateTime")
//    val rows: MutableList<List<String?>> = mutableListOf()
//
//    for (l in locations) {
//
//      val data: MutableList<String?> = mutableListOf()
//      for (prop in l.getProperties()) {
//        data.add(prop.get(l).toString())
//      }
//      rows.add(data)
//    }
//    return Convert(format, rows)
//  }
}
