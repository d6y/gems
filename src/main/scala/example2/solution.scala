package example2

object Status extends Enum {

  sealed abstract class EnumVal(override val name: String) extends Value

  val NotStarted = new EnumVal("n/a") {}
  val Pending    = new EnumVal("pending") {}
  val Complete   = new EnumVal("complete") {}
  val Rejected   = new EnumVal("rejected") {}
}

object Solution extends App {

  import Status._

  def finished_?(status: Status.EnumVal): Boolean =
    status == Complete || status == Rejected

  import Schema._, profile.simple._

  db.withSession { implicit session =>
    val report = for {
      payment <- payments if payment.status === Pending
    } yield payment
    println(report.run)
  }

  // compile error:  match may not be exhaustive
  /*
  def inProgress_?(status: Status.EnumVal): Boolean = status match {
    case Pending             => true
    case Complete | Rejected => false
  }
  */
}