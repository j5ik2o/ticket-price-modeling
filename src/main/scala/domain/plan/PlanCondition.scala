package domain.plan

import java.time.LocalDateTime

import domain.Customer

final case class PlanCondition(customer: Customer, localDateTime: LocalDateTime)
