import scala.collection.mutable.ListBuffer

object Application {

  import OptionsRPS._
  import scala.io.StdIn._

  private var playersSelection = ROCK
  private var computersSelection = OptionsRPS(scala.util.Random.nextInt(OptionsRPS.maxId))
  private var start = false
  private var scissorsChosen = 0
  private var rocksChosen = 0
  private var papersChosen = 0
  private var max = ROCK
  private var playerWins = 0
  private var computerWins = 0
  private var draws = 0
  private val rNum = scala.util.Random
  private val toFile = BufferOutput
  private val fromFile = BufferReader
  private var listOfWords: ListBuffer[String] = ListBuffer()
  private var playerName = ""

  def main(args: Array[String]):Unit = {

    println("Welcome to RPS")
    println("Please Enter your name")
    playerName = readLine("prompt> ")
    println("When you are ready type start")
    val startInput = readLine("prompt> ")
    if(startInput.equals("start")){
      println("Game Started")
      start = true
      toFile()
      fromFile()
    }
    gameStart()
  }

  def fromArray(): Unit ={
    for (value <- listOfWords if value.equals(ROCK.toString)) rocksChosen += 1
    for (value <- listOfWords if value.equals(PAPER.toString)) papersChosen += 1
    for (value <- listOfWords if value.equals(SCISSORS.toString)) scissorsChosen += 1
  }

  def toFile(file: String = playerName + ".txt", input: String = playersSelection.toString): Unit ={
    toFile.writeToFile(file, input+"\n")
  }

  def fromFile(file: String = playerName + ".txt"): Unit ={
    listOfWords = fromFile.readFromFile(file)
    fromArray()
  }

  def gameStart(): Unit ={
    while(start) {
      println("Please enter one of the following:\n Rock || Paper || Scissors\n Type end to stop the game")
      selectionCheck(readLine("prompt> ").toUpperCase.charAt(0).toString)
      maxCalculation()
      if (playersSelection != null) {
        if (rNum.nextInt(50) > 35) {
          computerSelection()
        } else {
          computersSelection = OptionsRPS(scala.util.Random.nextInt(OptionsRPS.maxId))
        }
        maxCounter()
        decision()
      } else {
        println("Invalid input")
      }

      println("\nWins: " + playerWins + " Losses: " + computerWins + " Draws: " + draws + "\n")
      toFile()
    }
    println("Game Ended, thank you for playing!")
  }

  def decision(): Unit = (playersSelection, computersSelection) match{
    case (ROCK, PAPER) => computerWin()
    case (ROCK, SCISSORS) => playerWin()
    case (ROCK, ROCK) => draw()
    case (PAPER, ROCK) => playerWin()
    case (PAPER, SCISSORS) => computerWin()
    case (PAPER, PAPER) => draw()
    case (SCISSORS, ROCK) => computerWin()
    case (SCISSORS, PAPER) => playerWin()
    case (SCISSORS, SCISSORS) => draw()
    case _ => println("I have no idea how it has reached this case?!")
  }

  def playerWin(): Unit ={
    println("\nYou won!\n")
    playerWins += 1
  }

  def computerWin(): Unit = {
    println("\nComputer won!\n")
    computerWins += 1
  }

  def draw(): Unit = {
    println("\nYou both LOSE!\n")
    draws += 1
  }

  def maxCounter(): Unit = {
    if(playersSelection == ROCK){rocksChosen += 1}
      else if(playersSelection == PAPER){papersChosen += 1}
      else {scissorsChosen += 1}
  }

  def computerSelection(): Unit = max match {
    case ROCK => computersSelection = PAPER
    case PAPER => computersSelection = SCISSORS
    case SCISSORS => computersSelection = ROCK
  }

  def maxCalculation(): Unit ={
    if(rocksChosen > papersChosen && rocksChosen > papersChosen){max = ROCK}
      else if(papersChosen > rocksChosen && papersChosen > scissorsChosen){max = PAPER}
      else {max = SCISSORS}
  }

  def selectionCheck(selection: String): Unit = selection match {
    case "R" => playersSelection = ROCK
    case "P" => playersSelection = PAPER
    case "S" => playersSelection = SCISSORS
    case "E" => start = false
    case _ => playersSelection = OptionsRPS(scala.util.Random.nextInt(OptionsRPS.maxId))
  }
}
