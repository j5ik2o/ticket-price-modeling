package domain

final case class Identifications(breachEncapsulationOfValue: Seq[Identification]) {

  def contains(identification: Identification): Boolean =
    breachEncapsulationOfValue.contains(identification)

  def exists(f: Identification => Boolean): Boolean =
    breachEncapsulationOfValue.exists(f)

}

object Identifications {

  val empty: Identifications = apply(Seq.empty)

  def apply(values: Seq[Identification]): Identifications = new Identifications(values)

  def fromValues(values: Identification*): Identifications = new Identifications(values.toSeq)

}
