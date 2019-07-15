package domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

sealed trait Age extends Ordered[Age] {
  def breachEncapsulationOfValue(today: LocalDate): Int

  override def compare(that: Age): Int = {
    val now = LocalDate.now()
    breachEncapsulationOfValue(now) compareTo that.breachEncapsulationOfValue(now)
  }

}

object Age {

  def fromBirthDay(birthDay: LocalDate): Age =
    DynamicAge(birthDay)

  def apply(value: Int): Age = StaticAge(value)

  private final case class StaticAge(value: Int) extends Age {
    override def breachEncapsulationOfValue(today: LocalDate): Int = value
  }

  private final case class DynamicAge(birthDay: LocalDate) extends Age {
    private def calculateAge(birthDay: LocalDate, today: LocalDate): Int =
      ChronoUnit.YEARS.between(birthDay, today).toInt
    def breachEncapsulationOfValue(today: LocalDate): Int = calculateAge(birthDay, today)
  }

}
