package domain.plan

import java.time.{DayOfWeek, LocalDateTime}

import domain.support.Specification
import enumeratum._

sealed trait BusinessDaySpecification
    extends Specification[LocalDateTime]
    with EnumEntry

object BusinessDaySpecification extends Enum[BusinessDaySpecification] {
  override def values: IndexedSeq[BusinessDaySpecification] = findValues

  case object Weekday extends BusinessDaySpecification {
    override def isSatisfiedBy(localDateTime: LocalDateTime): Boolean =
      !Holiday.isSatisfiedBy(localDateTime)
  }

  case object Holiday extends BusinessDaySpecification {
    override def isSatisfiedBy(localDateTime: LocalDateTime): Boolean =
      Seq(DayOfWeek.SUNDAY, DayOfWeek.THURSDAY)
        .contains(localDateTime.getDayOfWeek)
  }

}
