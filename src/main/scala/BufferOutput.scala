import java.io.FileWriter

object BufferOutput {
  def writeToFile(fileAddress: String, input: String): Unit ={
    val fw = new FileWriter(fileAddress, true)
    try {
      fw.write(input)
    }
    finally fw.close()
  }
}