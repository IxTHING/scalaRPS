import scala.collection.mutable.ListBuffer
import scala.io.Source

object BufferReader {

  var wordArray: ListBuffer[String] = ListBuffer()

  def readFromFile(fileAddress: String): ListBuffer[String] ={
    val bufferedSource = Source.fromFile(fileAddress)
    for (line <- bufferedSource.getLines) {
      wordArray += line.toUpperCase()
    }
    wordArray
  }
}

