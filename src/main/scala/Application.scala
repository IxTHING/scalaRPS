object Application {

  import OptionsRPS._
  import scala.io.StdIn._

  private var playersSelection = ROCK
  private var computersSelection = ROCK
  private var start = false
  private var scissorsChosen = 0
  private var rocksChosen = 0
  private var papersChosen = 0
  private var max = ROCK
  private var playerWins = 0
  private var computerWins = 0
  private var draws = 0

  def main(args: Array[String]):Unit = {
    println("Welcome to RPS")
    println("When you are ready type start")
    val startInput = readLine("prompt> ")
    if(startInput == "start"){
      println("Game Started")
      start = true
    }
    gameStart()
  }

  def gameStart(): Unit ={
    while(start){
      println("Please enter one of the following:\n Rock || Paper || Scissors\n Type end to stop the game")
      selectionCheck(readLine("prompt> ").toUpperCase.charAt(0).toString)
      maxCalculation()
      println(playersSelection)
      if(playersSelection != null) {
        computerSelection()
        maxCounter()
        decision()
      } else {println("Invalid input")}
    }
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
    println("You won!")
    playerWins += 1
  }

  def computerWin(): Unit = {
    println("Computer won!")
    computerWins += 1
  }

  def draw(): Unit = {
    println("You both LOSE!")
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
    case _ => playersSelection = null
  }
}
