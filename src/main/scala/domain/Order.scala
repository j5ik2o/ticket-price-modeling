package domain

import java.time.LocalDateTime

import domain.plan.{ Plan, Plans, Price }

final case class Order(customer: Customer, orderedAt: LocalDateTime) {

  def plan: Option[Plan] = {
    Plans.all.lowestPriceOrder
      .filterByLocalDateTime(orderedAt)
      .findByCustomer(customer, orderedAt)
  }

}
