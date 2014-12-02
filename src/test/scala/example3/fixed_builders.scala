package example3

case class TestApplicant(
  number:    String="002",
  surname:   String="Smith",
  forenames: Option[String]=Some("Colin")) {

  def withNoForenames: TestApplicant = copy(forenames=None)

  def create: Applicant = Applicant(number, surname, forenames)
}

case class TestCsvRow(
  number:    String="000",
  forenames: Option[String]=Some("Diana")) {

  def matching(a: TestApplicant): TestCsvRow = copy(number=a.number)
  def withForenames(fn: String): TestCsvRow = copy(forenames=Some(fn))

  def create: CsvRow =
    CsvRow("2014", Code.Important, "001",
      number,
      Choice.Third, Some("Smith"),
      forenames)
}