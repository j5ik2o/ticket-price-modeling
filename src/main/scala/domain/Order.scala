package domain

import java.time.LocalDateTime

import domain.plan.{ Plan, Plans, Price }

final case class Order(private val customer: Customer, private val orderedAt: LocalDateTime) {

  def plan: Option[Plan] = {
    Plans.all.lowestPriceOrder
      .filterByLocalDateTime(orderedAt)
      .findByCustomer(customer, orderedAt)
  }

}
