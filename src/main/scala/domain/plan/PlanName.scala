package domain.plan

import enumeratum._

sealed abstract class PlanName(override val entryName: String) extends EnumEntry

object PlanName extends Enum[PlanName] {
  override def values = findValues
  case object CinemaCitizen                            extends PlanName("シネマシティズン")
  case object CinemaCitizenSenior                      extends PlanName("シネマシティズン(60才以上)")
  case object General                                  extends PlanName("一般")
  case object Senior                                   extends PlanName("シニア(70才以上)")
  case object UniversityStudent                        extends PlanName("学生(大・専)")
  case object HighSchoolStudent                        extends PlanName("中・高校生")
  case object ElementarySchoolStudentAndUnder5YearsOld extends PlanName("幼児(3才以上)・小学生")
  case object DisabilityUniversityStudent              extends PlanName("障がい者(学生以上)")
  case object DisabilityUnderHighSchoolStudent         extends PlanName("障がい者(高校生以下)")
}
