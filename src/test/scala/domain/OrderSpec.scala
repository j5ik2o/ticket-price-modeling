package domain

import java.time.{ LocalDate, LocalDateTime }

import domain.plan.{ PlanName, Price }
import org.scalatest.{ FreeSpec, Matchers }

class OrderSpec extends FreeSpec with Matchers {

  "Order" - {
    "Holiday" - {
      "Membership" in {
        val customer =
          Customer(
            LocalDate.now().minusYears(20),
            Gender.Male,
            Identifications.fromValues(Identification.MembershipCard)
          )
        val plan = Order(customer, LocalDateTime.of(2019, 7, 14, 12, 0)).plan
        plan.map(_.breachEncapsulationOfName) shouldBe Some(PlanName.CinemaCitizen)
        plan.map(_.breachEncapsulationOfPrice) shouldBe Some(Price(1300))
      }
      "MembershipSenior" in {
        val customer =
          Customer(
            LocalDate.now().minusYears(60),
            Gender.Male,
            Identifications.fromValues(Identification.MembershipCard)
          )
        val plan = Order(customer, LocalDateTime.of(2019, 7, 14, 12, 0)).plan
        plan.map(_.breachEncapsulationOfName) shouldBe Some(PlanName.CinemaCitizenSenior)
        plan.map(_.breachEncapsulationOfPrice) shouldBe Some(Price(1000))
      }
      "General" in {
        val customer =
          Customer(LocalDate.now().minusYears(20), Gender.Male, Identifications.empty)
        val plan = Order(customer, LocalDateTime.of(2019, 7, 14, 12, 0)).plan
        plan.map(_.breachEncapsulationOfName) shouldBe Some(PlanName.General)
        plan.map(_.breachEncapsulationOfPrice) shouldBe Some(Price(1800))
      }

    }

  }

}
