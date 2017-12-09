package com.roulette.bet

/**
  * Created by harshitha.suresh on 01/12/2017.
  */
class OddEvenBetType(remainder: Int) extends BetType {
  private val MULTIPLICAND: BigDecimal = BigDecimal.valueOf(2)

  override def isMatched(rouletteNumber: Int): Boolean = rouletteNumber % 2 == remainder

  override def getWinningsForBetAmount(amount: BigDecimal): BigDecimal = amount * MULTIPLICAND

  override def asString: String = if (remainder == 0) "EVEN" else "ODD"
}
