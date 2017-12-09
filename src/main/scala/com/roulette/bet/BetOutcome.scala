package com.roulette.bet

/**
  * Created by harshitha.suresh on 01/12/2017.
  */
class BetOutcome(val bet: Bet, val betOutcomeType: BetOutcomeType, val winningsAmount: BigDecimal)

object BetOutcome {
  def computeTotalWinnings(betOutcomes: List[BetOutcome]): BigDecimal = betOutcomes.toStream.map(_.winningsAmount).sum

  def computeTotalBetAmount(betOutcomes: List[BetOutcome]) = betOutcomes.toStream.map(_.bet.amount.intValue()).sum

}

sealed abstract class BetOutcomeType() {
  def outcome: String
  override def toString = s"$outcome"
}
case object WinBetOutcomeType extends BetOutcomeType { val outcome = "WIN" }
case object LossBetOutcomeType extends BetOutcomeType { val outcome = "LOSS" }
