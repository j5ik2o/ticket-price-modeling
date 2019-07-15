package domain

import java.time.LocalDateTime

import domain.plan.{Plans, Price}

final case class Order(customer: Customer, orderedAt: LocalDateTime) {

  def price: Option[Price] = {
    Plans.all.lowestPriceOrder
      .filterByLocalDateTime(orderedAt)
      .findByCustomer(customer, orderedAt)
      .map(_.price)
  }

}
