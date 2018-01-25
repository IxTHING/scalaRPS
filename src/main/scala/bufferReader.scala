import scala.collection.mutable.ListBuffer
import scala.io.{BufferedSource, Source}

object bufferReader {

  var wordArray: ListBuffer[String] = ListBuffer()

  def readFromFile(fileAddress: String): ListBuffer[String] ={
    val bufferedSource = Source.fromFile(fileAddress)
    for (line <- bufferedSource.getLines) {
      println(line.toUpperCase)
      wordArray += line.toUpperCase()
    }
    wordArray
  }
}

/*Might not overwrite current text file?
val fw = new FileWriter("test.txt", true)
try {
  fw.write( /* your stuff */)
}
finally fw.close()
*/