package pl.edu.pw.elka.sag.agents

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import com.typesafe.scalalogging.Logger

import akka.actor.Actor
import akka.event.Logging
import pl.edu.pw.sagextract.KeyPhrase

class TextProcessingActor() extends Actor with TextReader {
  
  val log = Logging(context.system, this)
  var requestName:String = null
 
  def receive = {
    case request: TextProcessingActorRequest => {
      requestName = request.requestName
      log.debug("received message")

      val extractor = KeyPhraseExtractorFactory.getInstance
      val phrases = extractor.extractKeyphrases(readFile(request.file)).asScala.toList
      KeyPhraseExtractorFactory.realeaseInstance(extractor)
      printPhrases(phrases)      
      sender ! "ended"
    }
    
    case _ => log.error("Wrong message ")
    
  }
  
  def printPhrases(phrases: List[KeyPhrase]) = {
    
    val sortedPhrases = phrases.sortWith((p1, p2) => p1.getWeight > p2.getWeight)
    var groupList: ListBuffer[Integer] = ListBuffer()
    
    def printPhrase(i: Int) {
//      Thread sleep 100
      if (i + 1 > sortedPhrases.size) {
        log.debug(requestName + " - could not map to any group")
        return
      }
      val phrase = sortedPhrases(i)
      val group = Mapper.mapping.get(phrase.getPhrase.replace(" ", "_"))
      group match {
        case Some(g) => {
          groupList += g
          log.debug(requestName + " - Found group for phrase: " + phrase)
          printPhrase(i + 1)
        }  
        case None => {
          log.debug(requestName + " - No group for phrase: " + phrase)
          printPhrase(i + 1)
        }
      }
    }
    printPhrase(0)
    var output = requestName + " "
    groupList.foreach { group => output += group + ", " }
    log.info(output)
  }
}
