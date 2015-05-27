package pl.edu.pw.elka.sag.agents

import java.io.File

/**
 * @author marcin
 */
class TextProcessingActorRequest(_requestName: String, _file: File) {
  
  val file = _file
  val requestName = _requestName
}

object TextProcessingActorRequest {
  def apply(fileName: String, file: File) = new TextProcessingActorRequest(fileName, file)
}