package example1

object Solution extends App {

  import com.typesafe.config.{Config,ConfigFactory}

  // Fail fast by using strict vals to lookup configuration values:
  class Settings(conf: Config) {
    val dbUser: String = conf.getString("db.user")
  }

  val settings = new Settings(ConfigFactory.load())

  // Without a configuration value...
  // com.typesafe.config.ConfigException$Missing:
  //   No configuration setting found for key 'db'

  def doWork(conf: Settings): Unit = {
    println(s"Using ${conf.dbUser}")
  }

  doWork(settings)
}