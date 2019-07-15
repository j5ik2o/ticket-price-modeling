package domain

final case class Identifications(breachEncapsulationOfValue: Seq[Identification]) {

  def contains(identification: Identification): Boolean =
    breachEncapsulationOfValue.contains(identification)

  def exists(f: Identification => Boolean): Boolean =
    breachEncapsulationOfValue.exists(f)

}
