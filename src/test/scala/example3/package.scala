package object example3 {

  // Dummy implementations so the code compiles

  object Code extends Enumeration {
    val Important = Value
  }

  object Choice extends Enumeration {
    val Third = Value
  }

  object Processor {
    def diff(a: Applicant, in: CsvRow): Option[NameChange] = {
      val changed_? = in.number == a.number && in.forenames != a.forenames
      if (changed_?) Some(NameChange()) else None
    }
  }
}