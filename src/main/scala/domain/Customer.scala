package domain

import java.time.LocalDate

final case class Customer(private val birthDay: LocalDate, private val gender: Gender, private val identifications: Identifications) {

  def hasIdentification(identification: Identification): Boolean = identifications.contains(identification)

  def age: Age = Age.fromBirthDay(birthDay)

}
