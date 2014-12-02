package example3

// The set up for this example is the idea of testing some code which is designed
// to detect a change.
//
// This example uses three pieces of data:
// 1. An "applicant" (think: person) that is used as an example of some state.
// 2. A row of data, which is an example of some input which might change our state.
// 3. A change, which is just "NameChange", but you could imagine others.

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