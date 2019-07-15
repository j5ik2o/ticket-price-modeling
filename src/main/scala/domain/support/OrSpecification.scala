package domain.support

case class OrSpecification[T](spec1: Specification[T], spec2: Specification[T])
    extends Specification[T] {

  override def isSatisfiedBy(t: T): Boolean =
    spec1.isSatisfiedBy(t) || spec2.isSatisfiedBy(t)

}
