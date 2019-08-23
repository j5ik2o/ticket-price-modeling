package domain.plan

import java.time.LocalDateTime

import domain.Customer

final case class PlanCondition(private val customer: Customer, private val localDateTime: LocalDateTime) {
  def breachEncapsulationOfCustomer: Customer = customer
  def breachEncapsulationOfLocalDateTime: LocalDateTime = localDateTime
}
