package pl.edu.pw.elka.sag.agents;

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
 
object Main extends App with TextReader {
    val system = ActorSystem("SagSystem")
       
    val files = filesInFolder("C:\\Studia\\scalaProjects\\texts")
    
    files.foreach { file => {
       val actor = system.actorOf(Props(new ArticleProcessingActor()), name = file.getName)
       actor ! readFile(file)
      }
    }
   
}
