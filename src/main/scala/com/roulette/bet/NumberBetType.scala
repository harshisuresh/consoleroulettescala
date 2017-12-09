package com.roulette.bet

/**
  * Created by harshitha.suresh on 01/12/2017.
  */
class NumberBetType(chosenNumber : Int) extends BetType {
  private val MULTIPLICAND: BigDecimal = BigDecimal.valueOf(36);

  override def isMatched(rouletteNumber: Int): Boolean = chosenNumber == rouletteNumber

  override def getWinningsForBetAmount(amount: BigDecimal): BigDecimal = amount * MULTIPLICAND

  override def asString: String = chosenNumber.toString
}
