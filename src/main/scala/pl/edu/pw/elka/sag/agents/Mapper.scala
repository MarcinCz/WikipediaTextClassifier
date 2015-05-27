package pl.edu.pw.elka.sag.agents

import scala.collection.JavaConverters._
import java.io.InputStream
import pl.edu.pw.elka.sag.TitleToGroupMapper
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

class Mapper(inputStream: InputStream) {
  
  val logger = Logger(LoggerFactory.getLogger(getClass.getName))
  
  private[this] val titleToGroupMapper = new TitleToGroupMapper(inputStream)
  titleToGroupMapper.readGroups()
  
  val titleGroupMapping = titleToGroupMapper.getTitleToGroupMapping
  logger.info(s"Loaded ${titleGroupMapping.size()} groups")

}

object Mapper {

  private[this] var _mapping:Map[String, Integer] = null
  def load = { _mapping = new Mapper(Mapper.getClass.getResourceAsStream("/input.txt")).titleGroupMapping.asScala.toMap}
  
  def mapping = _mapping

}