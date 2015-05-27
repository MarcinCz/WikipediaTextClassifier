package pl.edu.pw.elka.sag.agents

import com.typesafe.config.ConfigFactory

/**
 * @author marcin
 */
object AppConfig {
  private[this] val config = ConfigFactory.load()
  
  def poolSize = config.getInt("poolSize")
  def folderPath = config.getString("folderPath")
}