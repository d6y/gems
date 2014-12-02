package example3

import org.scalacheck._, Arbitrary._, Gen._

object Builders {

  implicit val applicant: Arbitrary[Applicant] = Arbitrary(applicantGenerator)

  def applicantGenerator: Gen[Applicant] =
    for {
      number    <- tenRandomDigits
      surname   <- arbitrary[String]
      forenames <- randomOptionalName
    } yield Applicant(number, surname, forenames)

  def randomOptionalName =
    Gen.oneOf(arbitrary[Option[String]], const(None))

  def tenRandomDigits =
    Gen.listOfN(10, Gen.numChar).map(_.mkString)


  implicit class ApplicantBuider(app: Applicant) {
    def withNoForenames: Applicant = app.copy(forenames=None)
  }

  // Same pattern for random CSV Row
  // ...

  implicit val row: Arbitrary[CsvRow] = Arbitrary(csvRowGenerator)

  def csvRowGenerator: Gen[CsvRow] =
    for {
      year      <- Gen.listOfN(4, Gen.numChar).map(_.mkString)
      code      <- Gen.oneOf(Code.Important, Code.Secret)
      personId  <- tenRandomDigits
      number    <- tenRandomDigits
      choice    <- Gen.oneOf(Choice.Third, Choice.Second)
      surname   <- randomOptionalName
      forenames <- randomOptionalName
      } yield CsvRow(year,code,personId,number,choice,surname,forenames)


  implicit class RowBuider(row: CsvRow) {
    def matching(a: Applicant): CsvRow = row.copy(number=a.number)
    def withForenames(fn: String): CsvRow = row.copy(forenames=Some(fn))
   }

}