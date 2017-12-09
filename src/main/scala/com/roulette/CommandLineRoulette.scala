package com.roulette

import java.math.BigDecimal

import com.roulette.bet.{BetType, Bet}
import com.roulette.exceptions.UserInputException
import com.roulette.player.Player
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by harshitha.suresh on 01/12/2017.
  */
@Service
class CommandLineRoulette {
  @Autowired
  val rouletteEngine : RouletteEngine = null

  def startGame : Thread = {
    val thread = new Thread {
      override def run() {
        readUserInput
      }
    }
    thread.start()
    thread
  }

  def readUserInput : Option[Bet] = {
    println("Please enter your name, bet and bet amount")
    println("Example 1: Name, bet number(1-36 or ODD or EVEN), amount")
    val userInput = scala.io.StdIn.readLine()
    userInput match {
      case "EXIT" =>  Option(null)
      case _ =>
        parseUserInput(userInput)
        readUserInput
    }
  }
  def parseUserInput(userInput : String) = {
    val splitUserInput = userInput.split(",").map(_.trim)
    val playerName: String = splitUserInput(0)
    val bet: String = splitUserInput(1)
    val betAmount: BigDecimal = new BigDecimal(splitUserInput(2))

    val betType = BetType.parseBetInput(bet)

    if (betType.isEmpty) {
      throw new UserInputException("Invalid bet type:" + betType)
    }
    val userBet = new Bet(new Player(playerName), betAmount, betType.get)
    rouletteEngine.placeBet(userBet)
  }
}
