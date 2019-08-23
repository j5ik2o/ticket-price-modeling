package domain.plan

final case class Price(private val amount: Int) {
  require(0 < amount)
  def breachEncapsulationOfAmount: Int = amount
}
