package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

interface IConvertable {
  fun getProperties(): List<Pair<String, String>>
}
