package example3

// Fake data structures to make the code compile

case class CsvRow(
  year:      String,
  code:      Code.Value,
  personId:  String,
  number:    String,
  choice:    Choice.Value,
  surname:   Option[String],
  forenames: Option[String]
)

case class Applicant(
  number:    String,
  surname:   String,
  forenames: Option[String]
)

case class NameChange()