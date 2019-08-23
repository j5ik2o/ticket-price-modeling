package domain

final case class Identifications(private val value: Seq[Identification]) {

  def contains(identification: Identification): Boolean =
    value.contains(identification)

  def exists(f: Identification => Boolean): Boolean =
    value.exists(f)

  def breachEncapsulationOfValue: Seq[Identification] = value

}

object Identifications {

  val empty: Identifications = apply(Seq.empty)

  def apply(values: Seq[Identification]): Identifications = new Identifications(values)

  def fromValues(values: Identification*): Identifications = new Identifications(values.toSeq)

}
