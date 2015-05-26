package pl.edu.pw.elka.sag.agents

import java.io.File
import java.util.Arrays
import java.nio.file.Paths
import java.nio.file.Files

trait TextReader {

  def filesInFolder(folderName: String): Array[File] = {
    val folder = new File(folderName);
    folder.listFiles()
  }
  
  def readFile(file: File):String = {
    new String(Files.readAllBytes(Paths.get(file.getPath())))
  }
}