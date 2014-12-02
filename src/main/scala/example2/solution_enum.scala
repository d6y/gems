package example2

object StatusEnum extends Enumeration {
  type Status    = Value

  val NotStarted = Value("n/a")
  val Pending    = Value("pending")
  val Complete   = Value("complete")
  val Rejected   = Value("rejected")
}

object EnumSolution extends App {

  import StatusEnum._

  def isFinished(status: Status): Boolean =
    status == Complete || status == Rejected

  def isInProgress(status: Status): Boolean =
    status match {
      case Pending             => true
      case Complete | Rejected => false
    }
}