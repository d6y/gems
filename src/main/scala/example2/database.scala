package example2

import scala.slick.driver.PostgresDriver.simple._

object Schema extends {
 val profile = scala.slick.driver.PostgresDriver
 } with ProblemTables with Tables {
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