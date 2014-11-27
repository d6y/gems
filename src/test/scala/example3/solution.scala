package example3

import org.specs2._

class SolutionSpec extends Specification {

  def is = s2"""
    The processor should
      handle name changes    $nc
  """

  def nc = {
    // If this was in the database...
    val applicant = TestApplicant() withNoForenames

    // ...and we received this change...
    val input = TestCsvRow() matching(applicant) withForenames "Alice"

    // ...the action must be a name change:
    Processor.diff(applicant.create, input.create) must beSome(NameChange())
  }
}








