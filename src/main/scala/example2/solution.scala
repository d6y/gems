package example2

object Status {

  sealed abstract class Value(val name: String)

  case object NotStarted extends Value("n/a")
  case object Pending    extends Value("pending")
  case object Complete   extends Value("complete")
  case object Rejected   extends Value("rejected")

  val values = Seq(NotStarted, Pending, Complete, Rejected)
}

object Solution extends App {

  import Status._

  def finished_?(status: Status.Value): Boolean =
    status == Complete || status == Rejected

  // Compile error:  match may not be exhaustive
  // It would fail on the following input: NotStarted
  /*
  def inProgress_?(status: Status.Value): Boolean =
    status match {
      case Pending             => true
      case Complete | Rejected => false
    }
  */
}