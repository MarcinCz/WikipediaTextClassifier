package pl.edu.pw.elka.sag.agents

/**
 * @author marcin
 */
object AgentSystemTimer {
  private[this] var _start = 0l
  private[this] var _end = 0l
  
  def start = {_start = System.nanoTime}
  def stop = {_end = System.nanoTime}
  def time = {_end - _start}
}