package domain

import java.time.LocalDate

final case class Customer(birthDay: LocalDate,
                          gender: Gender,
                          identifications: Identifications) {

  def age(today: LocalDate): Age = Age.fromBirthDay(today)

}
