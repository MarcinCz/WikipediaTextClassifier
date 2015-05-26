package pl.edu.pw.elka.sag.agents

import java.io.InputStream

import pl.edu.pw.elka.sag.TitleToGroupMapper;

class Mapper(inputStream: InputStream) {
  
  private[this] val titleToGroupMapper = new TitleToGroupMapper(inputStream)
  titleToGroupMapper.readGroups()
  
  val titleGroupMapping = titleToGroupMapper.getTitleToGroupMapping
  
}

object Mapper {

  val mapping = new Mapper(Mapper.getClass.getResourceAsStream("/input.txt")).titleGroupMapping

}