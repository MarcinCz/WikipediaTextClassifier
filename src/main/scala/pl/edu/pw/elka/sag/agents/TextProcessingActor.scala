package pl.edu.pw.elka.sag.agents

import akka.actor.Actor
import akka.event.Logging
import pl.edu.pw.sagextract.KeyPhrase
import scala.collection.JavaConverters._

class ArticleProcessingActor() extends Actor {
  
  val log = Logging(context.system, this)
  
 
  def receive = {
    case text: String => {
      log.debug("received message")

      val phrases = KeyPhraseExtractor().extractKeyphrases(text).asScala.toList
      printPhrases(phrases)      
    }
    
    case _ => log.error("Wrong message ")
    
  }
  
  private def printPhrases(phrases: List[KeyPhrase]) = {
    val sortedPhrases = phrases.sortWith((p1, p2) => p1.getWeight > p2.getWeight)
    
    def printPhrase(i: Int) {
      val phrase = sortedPhrases(i)
      val group = Option(Mapper.mapping.get(phrase.getPhrase.replace(" ", "_")))
      group match {
        case Some(g) => println ("Found group " + g + " for phrase: " + phrase)
        case None => {
          println("No group for phrase: " + phrase)
          printPhrase(i + 1)
        }
      }
    }
    printPhrase(0)
  }
}


object KeyPhraseExtractor {
  import pl.edu.pw.sagextract.KeyPhraseExtractor

  private[this] val extractor = new KeyPhraseExtractor
  
  def apply() = extractor
}