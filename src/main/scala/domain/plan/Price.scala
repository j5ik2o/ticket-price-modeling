package domain.plan

final case class Price(amount: Int) {
  require(0 < amount)
}
