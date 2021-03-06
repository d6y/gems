package example3

import org.specs2.mutable._

class ProblemSpec extends Specification {

  "The processor" should {
    "detect a name change" in {

      // If this was in the database...
      val applicant =
        Applicant(number="0000000002", surname="Smith", forenames=None)

      // ...and we received this change...
      val input = CsvRow(
        year      = "2014",
        code      = Code.Important,
        personId  = "000000001",
        number    = "0000000002",
        choice    = Choice.Third,
        surname   = Some("Smith"),
        forenames = Some("Alice")
        )

      // ...the action must be a name change:
      Processor.diff(applicant, input) must beSome(NameChange())

    }
  }
}