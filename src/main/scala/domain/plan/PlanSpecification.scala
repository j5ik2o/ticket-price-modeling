package domain.plan

import java.time.{ LocalDate, LocalDateTime }

import domain.Customer
import domain.support.Specification

final case class PlanSpecification(
    customerSpecs: Specification[Customer],
    businessDayWithLateSpec: Option[Specification[LocalDateTime]],
    movieDaySpec: Option[Specification[LocalDate]]
) extends Specification[PlanCondition] {
  (businessDayWithLateSpec, movieDaySpec) match {
    case (Some(_), Some(_)) =>
      throw new IllegalArgumentException
    case (None, None) =>
      throw new IllegalArgumentException
    case _ =>
  }
  override def isSatisfiedBy(arg: PlanCondition): Boolean = {
    customerSpecs.isSatisfiedBy(arg.customer) &&
    businessDayWithLateSpec.fold(true)(_.isSatisfiedBy(arg.localDateTime)) &&
    movieDaySpec
      .fold(true)(_.isSatisfiedBy(arg.localDateTime.toLocalDate))
  }

}
