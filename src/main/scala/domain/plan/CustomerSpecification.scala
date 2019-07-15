package domain.plan

import domain.support.Specification
import domain.{ Age, Customer, Identification }
import enumeratum._

sealed trait CustomerSpecification extends Specification[Customer] with EnumEntry

object CustomerSpecification extends Enum[CustomerSpecification] {
  override def values = findValues

  case object CinemaCitizen extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.identifications.contains(Identification.MembershipCard)
  }
  case object CinemaCitizenSenior extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.age >= Age(60)
  }
  case object General extends CustomerSpecification {
    override def isSatisfiedBy(arg: Customer): Boolean =
      !values
        .filterNot(_ == this).reduceLeft[Specification[Customer]](_ or _)
        .isSatisfiedBy(arg)
  }
  case object Senior extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.age >= Age(70)
  }
  case object UniversityStudent extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.identifications.contains(Identification.StudentCard.University)
  }
  case object HighSchoolStudent extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.identifications.contains(Identification.StudentCard.HighSchool)
  }
  case object ElementarySchoolStudent extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.identifications.contains(Identification.StudentCard.Elementary)
  }
  case object Under5YearsOld extends CustomerSpecification {
    override def isSatisfiedBy(customer: Customer): Boolean =
      customer.age <= Age(5)
  }
  case object Disability extends CustomerSpecification {
    override def isSatisfiedBy(arg: Customer): Boolean =
      arg.identifications.contains(Identification.DisabilityHandbook)
  }
}