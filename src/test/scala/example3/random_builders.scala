package example3

import org.scalacheck._, Arbitrary._, Gen._

object Builders {

  lazy val applicantGenerator: Gen[Applicant] =
    for {
      number    <- tenRandomDigits
      surname   <- arbitrary[String]
      forenames <- arbitrary[Option[String]]
    } yield Applicant(number, surname, forenames)

  val tenRandomDigits =
    listOfN(10, Gen.numChar).map(_.mkString)

  implicit class ApplicantOps(app: Applicant) {
    def withNoForenames: Applicant = app.copy(forenames=None)
  }

  implicit val applicant: Arbitrary[Applicant] = Arbitrary(applicantGenerator)

  // Same pattern for random CSV Row
  // ...

  implicit val row: Arbitrary[CsvRow] = Arbitrary(csvRowGenerator)

  lazy val csvRowGenerator: Gen[CsvRow] =
    for {
      year      <- Gen.listOfN(4, Gen.numChar).map(_.mkString)
      code      <- Gen.oneOf(Code.Important, Code.Secret)
      personId  <- tenRandomDigits
      number    <- tenRandomDigits
      choice    <- Gen.oneOf(Choice.Third, Choice.Second)
      surname   <- arbitrary[Option[String]]
      forenames <- arbitrary[Option[String]]
      } yield CsvRow(year,code,personId,number,choice,surname,forenames)


  implicit class RowBuider(row: CsvRow) {
    def matching(a: Applicant): CsvRow = row.copy(number=a.number)
    def withForenames(fn: String): CsvRow = row.copy(forenames=Some(fn))
   }

}