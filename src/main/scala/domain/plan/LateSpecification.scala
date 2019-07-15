package domain.plan

import java.time.LocalDateTime

import domain.support.Specification

final case class LateSpecification(startHour: Int)
    extends Specification[LocalDateTime] {
  override def isSatisfiedBy(arg: LocalDateTime): Boolean =
    arg.getHour >= startHour
}
