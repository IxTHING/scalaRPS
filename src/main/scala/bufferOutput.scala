import java.io.{BufferedWriter, File, FileWriter}

object bufferOutput {
  def writeToFile(fileAddress: String, input: String): Unit ={
    val file = new File(fileAddress)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(input)
    bw.close()
  }
}
