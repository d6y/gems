package example2

trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._

  import Status._

  implicit val statusColumnType =
    MappedColumnType.base[Value, String](
      _.name,
      s => Status.values.find(_.name == s) getOrElse NotStarted
    )

  case class Payment(status: Value, id: Long=0L)

  class Payments(tag: Tag) extends Table[Payment](tag, "payments") {
    def id     = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def status = column[Value]("status")
    def *      = (status, id) <> (Payment.tupled, Payment.unapply)
  }

  lazy val payments = TableQuery[Payments]

  def pending(implicit s: Session) =
    for {
      payment <- payments if payment.status === (Pending: Value)
    } yield payment
}

object DbSolution extends App {

  import Schema._, profile.simple._

  db.withSession { implicit session =>
    println(pending.run)
  }

}