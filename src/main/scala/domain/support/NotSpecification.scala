package domain.support

case class NotSpecification[T](spec1: Specification[T])
    extends Specification[T] {

  override def isSatisfiedBy(t: T): Boolean =
    !spec1.isSatisfiedBy(t)

}
