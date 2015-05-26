package pl.edu.pw.elka.sag.agents

import akka.actor.Actor


class MasterActor extends Actor{

  def receive: Receive =  { null }
/*  val workerActors = context.actorOf(RoundRobinPool(5).props(Props[WorkerActor]), name = "WorkerActors")

   def receive: Receive = {     
     case request => {       
       workerActors forward request
     }    
  }
  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Props(new ArticleProcessingActor(extractor = extractor)))
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }*/
}