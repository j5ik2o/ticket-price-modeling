package domain.plan

import java.time.LocalDate

import domain.support.Specification

case object MovieDaySpecification extends Specification[LocalDate] {
  override def isSatisfiedBy(arg: LocalDate): Boolean =
    arg.getDayOfMonth == 1
}
