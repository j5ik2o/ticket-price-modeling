package domain

import enumeratum._

sealed trait Identification extends EnumEntry

object Identification extends Enum[Identification] {
  override def values: IndexedSeq[Identification] = findValues
  case object MembershipCard     extends Identification
  case object DisabilityHandbook extends Identification

  sealed trait StudentCard extends Identification

  object StudentCard extends Enum[StudentCard] {
    override def values: IndexedSeq[StudentCard] = findValues
    case object University extends StudentCard
    case object HighSchool extends StudentCard
    case object Elementary extends StudentCard
  }
}
