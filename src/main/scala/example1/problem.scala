package example1

class Settings() {
  val dbUser: Option[String] = read("db.user")
}

object Problem extends App {

  def doTheWork(conf: Settings): Unit = {

    val user = try {
      conf.dbUser.get
    } catch {
      case nse: NoSuchElementException =>
        throw new IllegalArgumentException("Missing user setting")
    }

    // Actual work to be done would go here
 }

 doTheWork(new Settings())
}