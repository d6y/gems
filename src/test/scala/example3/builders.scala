package example3

// Incomplete builders

case class TestApplicant(
  number:    String="002",
  surname:   String="Smith",
  forenames: Option[String]=Some("Alice")) {

  def create: Applicant = Applicant(number, surname, forenames)
  def withNoForenames: TestApplicant = copy(forenames=None)
}

case class TestCsvRow(
  number:    String="000",
  forenames: Option[String]=Some("Bob")) {

  def create: CsvRow =
    CsvRow("2014", Code.Important, "001",
      number,
      Choice.Third, Some("Smith"),
      forenames)

  def matching(a: TestApplicant): TestCsvRow = copy(number=a.number)
  def withForenames(fn: String): TestCsvRow = copy(forenames=Some(fn))
}
