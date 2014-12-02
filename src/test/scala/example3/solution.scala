package example3

import org.specs2.mutable._

class SolutionSpec extends Specification {

  "The processor" should {
    "detect a name change" in {

      // If this was in the database...
      val applicant = TestApplicant() withNoForenames

      // ...and we received this change...
      val input = TestCsvRow() matching(applicant) withForenames "Alice"

      // ...the action must be a name change:
      Processor.diff(applicant.create, input.create) must beSome(NameChange())

    }
  }
}








