package domain.plan

import java.time.LocalDateTime

import domain.Customer

final case class Plans(breachEncapsulationOfValue: Seq[Plan]) {

  def filterByLocalDateTime(localDateTime: LocalDateTime): Plans =
    copy(breachEncapsulationOfValue.filter { plan =>
      val spec = plan.planSpec
      spec.businessDayWithLateSpec
        .fold(false)(_.isSatisfiedBy(localDateTime)) || spec.movieDaySpec.fold(false)(
        _.isSatisfiedBy(localDateTime.toLocalDate)
      )
    })

  def lowestPriceOrder: Plans =
    copy(breachEncapsulationOfValue.sortBy(_.price.amount))

  def findByCustomer(customer: Customer, localDateTime: LocalDateTime): Option[Plan] =
    breachEncapsulationOfValue.find(_.planSpec.isSatisfiedBy(PlanCondition(customer, localDateTime)))

  def combine(other: Plans): Plans =
    copy(breachEncapsulationOfValue ++ other.breachEncapsulationOfValue)

  def ++(other: Plans): Plans = combine(other)
}

object Plans {

  val lateSpec = LateSpecification(20)

  object WeekdayNotLatePlans {

    val cinemaCitizenPlan = Plan(
      PlanName.CinemaCitizen,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizen,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val cinemaCitizenSeniorPlan = Plan(
      PlanName.CinemaCitizenSenior,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizenSenior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val generalPlan = Plan(
      PlanName.General,
      Price(1800),
      PlanSpecification(
        customerSpecs = CustomerSpecification.General,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val seniorPlan = Plan(
      PlanName.Senior,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Senior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val universityStudentPlan = Plan(
      PlanName.UniversityStudent,
      Price(1500),
      PlanSpecification(
        customerSpecs = CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val highSchoolStudentPlan = Plan(
      PlanName.HighSchoolStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.HighSchoolStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val elementarySchoolStudentAndUnder5YearsOldPlan = Plan(
      PlanName.ElementarySchoolStudentAndUnder5YearsOld,
      Price(1000),
      PlanSpecification(
        customerSpecs =
          CustomerSpecification.ElementarySchoolStudent and CustomerSpecification.Under5YearsOld,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUniversityStudentPlan = Plan(
      PlanName.DisabilityUniversityStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Disability and CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUnderHighSchoolStudentPlan = Plan(
      PlanName.DisabilityUnderHighSchoolStudent,
      Price(900),
      PlanSpecification(
        CustomerSpecification.Disability and (CustomerSpecification.HighSchoolStudent or CustomerSpecification.ElementarySchoolStudent),
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val all =
      Plans(
        Seq(
          cinemaCitizenPlan,
          cinemaCitizenSeniorPlan,
          generalPlan,
          seniorPlan,
          universityStudentPlan,
          highSchoolStudentPlan,
          elementarySchoolStudentAndUnder5YearsOldPlan,
          disabilityUniversityStudentPlan,
          disabilityUnderHighSchoolStudentPlan
        )
      )
  }

  object WeekdayLatePlans {

    val cinemaCitizenPlan = Plan(
      PlanName.CinemaCitizen,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizen,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val cinemaCitizenSeniorPlan = Plan(
      PlanName.CinemaCitizenSenior,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizenSenior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val generalPlan = Plan(
      PlanName.General,
      Price(1300),
      PlanSpecification(
        customerSpecs = CustomerSpecification.General,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val seniorPlan = Plan(
      PlanName.Senior,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Senior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val universityStudentPlan = Plan(
      PlanName.UniversityStudent,
      Price(1300),
      PlanSpecification(
        customerSpecs = CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val highSchoolStudentPlan = Plan(
      PlanName.HighSchoolStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.HighSchoolStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val elementarySchoolStudentAndUnder5YearsOldPlan = Plan(
      PlanName.ElementarySchoolStudentAndUnder5YearsOld,
      Price(1000),
      PlanSpecification(
        customerSpecs =
          CustomerSpecification.ElementarySchoolStudent and CustomerSpecification.Under5YearsOld,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUniversityStudentPlan = Plan(
      PlanName.DisabilityUniversityStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Disability and CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUnderHighSchoolStudentPlan = Plan(
      PlanName.DisabilityUnderHighSchoolStudent,
      Price(900),
      PlanSpecification(
        CustomerSpecification.Disability and (CustomerSpecification.HighSchoolStudent or CustomerSpecification.ElementarySchoolStudent),
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and lateSpec),
        movieDaySpec = None
      )
    )

    val all =
      Plans(
        Seq(
          cinemaCitizenPlan,
          cinemaCitizenSeniorPlan,
          generalPlan,
          seniorPlan,
          universityStudentPlan,
          highSchoolStudentPlan,
          elementarySchoolStudentAndUnder5YearsOldPlan,
          disabilityUniversityStudentPlan,
          disabilityUnderHighSchoolStudentPlan
        )
      )
  }

  object HolidayNotLatePlans {

    val cinemaCitizenPlan = Plan(
      PlanName.CinemaCitizen,
      Price(1300),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizen,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val cinemaCitizenSeniorPlan = Plan(
      PlanName.CinemaCitizenSenior,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizenSenior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val generalPlan = Plan(
      PlanName.General,
      Price(1800),
      PlanSpecification(
        customerSpecs = CustomerSpecification.General,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val seniorPlan = Plan(
      PlanName.Senior,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Senior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val universityStudentPlan = Plan(
      PlanName.UniversityStudent,
      Price(1500),
      PlanSpecification(
        customerSpecs = CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val highSchoolStudentPlan = Plan(
      PlanName.HighSchoolStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.HighSchoolStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val elementarySchoolStudentAndUnder5YearsOldPlan = Plan(
      PlanName.ElementarySchoolStudentAndUnder5YearsOld,
      Price(1000),
      PlanSpecification(
        customerSpecs =
          CustomerSpecification.ElementarySchoolStudent or CustomerSpecification.Under5YearsOld,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUniversityStudentPlan = Plan(
      PlanName.DisabilityUniversityStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Disability and CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUnderHighSchoolStudentPlan = Plan(
      PlanName.DisabilityUnderHighSchoolStudent,
      Price(900),
      PlanSpecification(
        CustomerSpecification.Disability and (CustomerSpecification.HighSchoolStudent or CustomerSpecification.ElementarySchoolStudent),
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and !lateSpec),
        movieDaySpec = None
      )
    )

    val all =
      Plans(
        Seq(
          cinemaCitizenPlan,
          cinemaCitizenSeniorPlan,
          generalPlan,
          seniorPlan,
          universityStudentPlan,
          highSchoolStudentPlan,
          elementarySchoolStudentAndUnder5YearsOldPlan,
          disabilityUniversityStudentPlan,
          disabilityUnderHighSchoolStudentPlan
        )
      )
  }

  object HolidayLatePlans {

    val cinemaCitizenPlan = Plan(
      PlanName.CinemaCitizen,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizen,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val cinemaCitizenSeniorPlan = Plan(
      PlanName.CinemaCitizenSenior,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizenSenior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val generalPlan = Plan(
      PlanName.General,
      Price(1300),
      PlanSpecification(
        customerSpecs = CustomerSpecification.General,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val seniorPlan = Plan(
      PlanName.Senior,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Senior,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val universityStudentPlan = Plan(
      PlanName.UniversityStudent,
      Price(1300),
      PlanSpecification(
        customerSpecs = CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val highSchoolStudentPlan = Plan(
      PlanName.HighSchoolStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.HighSchoolStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val elementarySchoolStudentAndUnder5YearsOldPlan = Plan(
      PlanName.ElementarySchoolStudentAndUnder5YearsOld,
      Price(1000),
      PlanSpecification(
        customerSpecs =
          CustomerSpecification.ElementarySchoolStudent and CustomerSpecification.Under5YearsOld,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUniversityStudentPlan = Plan(
      PlanName.DisabilityUniversityStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Disability and CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val disabilityUnderHighSchoolStudentPlan = Plan(
      PlanName.DisabilityUnderHighSchoolStudent,
      Price(900),
      PlanSpecification(
        CustomerSpecification.Disability and (CustomerSpecification.HighSchoolStudent or CustomerSpecification.ElementarySchoolStudent),
        businessDayWithLateSpec = Some(BusinessDaySpecification.Holiday and lateSpec),
        movieDaySpec = None
      )
    )

    val all =
      Plans(
        Seq(
          cinemaCitizenPlan,
          cinemaCitizenSeniorPlan,
          generalPlan,
          seniorPlan,
          universityStudentPlan,
          highSchoolStudentPlan,
          elementarySchoolStudentAndUnder5YearsOldPlan,
          disabilityUniversityStudentPlan,
          disabilityUnderHighSchoolStudentPlan
        )
      )
  }

  object MovieDayPlans {

    val cinemaCitizenPlan = Plan(
      PlanName.CinemaCitizen,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizen,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val cinemaCitizenSeniorPlan = Plan(
      PlanName.CinemaCitizenSenior,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizenSenior,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val generalPlan = Plan(
      PlanName.General,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.General,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val seniorPlan = Plan(
      PlanName.Senior,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Senior,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val universityStudentPlan = Plan(
      PlanName.UniversityStudent,
      Price(1100),
      PlanSpecification(
        customerSpecs = CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val highSchoolStudentPlan = Plan(
      PlanName.HighSchoolStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.HighSchoolStudent,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val elementarySchoolStudentAndUnder5YearsOldPlan = Plan(
      PlanName.ElementarySchoolStudentAndUnder5YearsOld,
      Price(1000),
      PlanSpecification(
        customerSpecs =
          CustomerSpecification.ElementarySchoolStudent and CustomerSpecification.Under5YearsOld,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val disabilityUniversityStudentPlan = Plan(
      PlanName.DisabilityUniversityStudent,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.Disability and CustomerSpecification.UniversityStudent,
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val disabilityUnderHighSchoolStudentPlan = Plan(
      PlanName.DisabilityUnderHighSchoolStudent,
      Price(900),
      PlanSpecification(
        CustomerSpecification.Disability and (CustomerSpecification.HighSchoolStudent or CustomerSpecification.ElementarySchoolStudent),
        businessDayWithLateSpec = None,
        movieDaySpec = Some(MovieDaySpecification)
      )
    )

    val all =
      Plans(
        Seq(
          cinemaCitizenPlan,
          cinemaCitizenSeniorPlan,
          generalPlan,
          seniorPlan,
          universityStudentPlan,
          highSchoolStudentPlan,
          elementarySchoolStudentAndUnder5YearsOldPlan,
          disabilityUniversityStudentPlan,
          disabilityUnderHighSchoolStudentPlan
        )
      )
  }

  val all: Plans = WeekdayNotLatePlans.all ++ WeekdayLatePlans.all ++ HolidayNotLatePlans.all ++ HolidayLatePlans.all ++ MovieDayPlans.all

}