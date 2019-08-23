package domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

sealed trait Age extends Ordered[Age] {
  def asInt(today: LocalDate): Int

  override def compare(that: Age): Int = {
    val now = LocalDate.now()
    asInt(now) compareTo that.asInt(now)
  }

}

object Age {

  def fromBirthDay(birthDay: LocalDate): Age =
    DynamicAge(birthDay)

  def apply(value: Int): Age = StaticAge(value)

  private final case class StaticAge(value: Int) extends Age {
    require(0 < value)
    override def asInt(today: LocalDate): Int = value
  }

  private final case class DynamicAge(birthDay: LocalDate) extends Age {
    private def calculateAge(birthDay: LocalDate, today: LocalDate): Int =
      ChronoUnit.YEARS.between(birthDay, today).toInt
    def asInt(today: LocalDate): Int = calculateAge(birthDay, today)
  }

}
