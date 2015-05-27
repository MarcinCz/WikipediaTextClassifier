package pl.edu.pw.elka.sag.agents

import scala.collection.mutable.ListBuffer
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import pl.edu.pw.sagextract.KeyPhraseExtractor

/**
 * @author marcin
 */
object KeyPhraseExtractorFactory {
  
  val logger = Logger(LoggerFactory.getLogger(getClass.getName))

  var extractors: ListBuffer[KeyPhraseExtractor] = ListBuffer()
  
  private[this] val extractor = new KeyPhraseExtractor
  
  def createInstances = {
    for( i <- 0 until AppConfig.poolSize){
       extractors += new KeyPhraseExtractor()
       logger.info(f"KEA instance #$i created");
    }
  }
  
  def getInstance = {
    var extractor:KeyPhraseExtractor = null
    this.synchronized {
      extractor = extractors(0)
      extractors -= extractor
    }
    extractor
  }
  
  def realeaseInstance(extractor: KeyPhraseExtractor) = {
    this.synchronized {
      extractors += extractor
    }
  }
}