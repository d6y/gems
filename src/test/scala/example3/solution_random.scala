package example3

import org.specs2.mutable._
import org.specs2.ScalaCheck

class RandomSolutionSpec extends Specification with ScalaCheck {

  import Builders._

  "The processor" should {
    "detect a name change" in prop {
      (applicant: Applicant, input: CsvRow) =>

      Processor.diff(
        applicant withNoForenames,
        input matching(applicant) withForenames "Alice") must beSome(NameChange())
    }
  }
}
