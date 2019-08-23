package domain.plan

final case class Plan(private val name: PlanName, private val price: Price, private val  spec: PlanSpecification) {
  def breachEncapsulationOfName: PlanName = name
  def breachEncapsulationOfPrice: Price = price
  def breachEncapsulationOfSpec: PlanSpecification = spec
  def isSatisfiedBy(arg: PlanCondition): Boolean = spec.isSatisfiedBy(arg)
}
