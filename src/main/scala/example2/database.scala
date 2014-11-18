package example2

trait SolutionTables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._

  import Status._

  implicit val stausColumnType = MappedColumnType.base[Status.EnumVal, String](
    _.name,
    s => Status.values.find(_.name == s).get)

  case class SolutionPayment(status: Status.EnumVal, id: Long=0L)

  class SolutionPayments(tag: Tag) extends Table[SolutionPayment](tag, "solution_payments") {
    def id     = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def status = column[Status.EnumVal]("status")
    def *      = (status, id) <> (SolutionPayment.tupled, SolutionPayment.unapply)
  }

  lazy val payments = TableQuery[SolutionPayments]
}

import scala.slick.driver.PostgresDriver.simple._

object Schema extends {
 val profile = scala.slick.driver.PostgresDriver
 } with ProblemTables with SolutionTables {
   val db = Database.forURL("jdbc:postgresql:gem", user="richard", password="", driver = "org.postgresql.Driver")

   private def createTables(): Unit = {
    import scala.slick.jdbc.meta.MTable

    def exists[T <: Table[_]](table: TableQuery[T])(implicit session: Session): Boolean =
      MTable.getTables(table.baseTableRow.tableName).firstOption.isDefined

    db.withSession{ implicit s =>
      for (table <- problemPayments :: payments :: Nil)
        if (!exists(table)) table.ddl.create
    }
  }

  createTables()
}

trait ProblemTables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._

  case class ProblemPayment(status: String, id: Long=0L)

  class ProblemPayments(tag: Tag) extends Table[ProblemPayment](tag, "problem_payments") {
    def id     = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def status = column[String]("status")
    def *      = (status, id) <> (ProblemPayment.tupled, ProblemPayment.unapply)
  }

  lazy val problemPayments = TableQuery[ProblemPayments]
}