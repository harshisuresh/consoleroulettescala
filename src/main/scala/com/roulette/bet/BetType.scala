package com.roulette.bet


/**
  * Created by harshitha.suresh on 01/12/2017.
  */
trait BetType {

  def isMatched(rouletteNumber: Int): Boolean

  def getWinningsForBetAmount(amount: BigDecimal): BigDecimal

  def asString: String
}

object BetType {
  val ROULETTE_SLOTS = "36"
  def parseBetInput(bet: String): Option[BetType] = {
    bet match {
      case "ODD" => Option.apply(new OddEvenBetType(1))
      case "EVEN" => Option.apply(new OddEvenBetType(0))
      case _ =>
        val betNumber: Int = bet.toInt
        if (betNumber > 0 && betNumber <= 36)  Option.apply(new NumberBetType(betNumber)) else  Option.empty
    }
  }
}