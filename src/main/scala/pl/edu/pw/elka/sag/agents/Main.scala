package pl.edu.pw.elka.sag.agents;

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.routing.RoundRobinPool
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.Future
 
object Main extends App with TextReader with TimePrinter {
    val system = ActorSystem("SagSystem")
    val files = filesInFolder(AppConfig.folderPath)
    
    time("KEA", KeyPhraseExtractorFactory.createInstances)
    time("Mapper", Mapper.load)
    AgentSystemTimer.start
    val master = system.actorOf(Props[MasterActor], name = "MasterActor")
    
    files.foreach { file => {
        master ! TextProcessingActorRequest(file = file, fileName = file.getName)
      }  
    }
   
}
