package domain.support

trait Specification[T] {
  def isSatisfiedBy(arg: T): Boolean

  def and(value: Specification[T]): Specification[T] =
    AndSpecification(this, value)

  def or(value: Specification[T]): Specification[T] =
    OrSpecification(this, value)

  def unary_! = NotSpecification(this)

}
