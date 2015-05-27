package pl.edu.pw.elka.sag.agents

import akka.actor.Actor
import akka.actor.Props
import akka.routing.RoundRobinPool
import akka.event.Logging


class MasterActor extends Actor with TimePrinter{

	val log = Logging(context.system, this)

  val workerActors = context.actorOf(Props[TextProcessingActor].withRouter(RoundRobinPool(AppConfig.poolSize)), name = "WorkerActors")   
  var repliesCount = 0
  var requestCount = 0
  
  def receive: Receive = {     
     case response: String => {
       repliesCount += 1
       log.info("Reply " + repliesCount + "/" + requestCount)
       if(repliesCount == requestCount) {
         AgentSystemTimer.stop
         printTime("Agents", AgentSystemTimer.time)
         context.system.shutdown()
       }
     }
     
     case request: TextProcessingActorRequest => {       
    	 requestCount += 1
       workerActors ! request
     }
     
     case _ => log.error("Wrong message ")
  }
}