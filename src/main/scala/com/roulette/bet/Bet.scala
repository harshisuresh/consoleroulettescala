package com.roulette.bet

import com.roulette.player.Player

/**
  * Created by harshitha.suresh on 01/12/2017.
  */
class Bet(val player: Player, val amount: BigDecimal, val betType: BetType ) {

  require(player != null, "player.Player cannot be null while construcing bet")
  require(amount !=null, "Amount cannot be null while construcing bet")
  require(amount.signum > 0, "com.roulette.bet.Bet amount must be > 0")
  require(betType != null, "com.roulette.bet.BetType cannot be null while construcing bet")

  def isMatched(rouletteNumber : Int) = betType.isMatched(rouletteNumber)

  // When should I use pattern matching over if else?
  def computeBetOutcome(rouletteOutcome: Int): BetOutcome = {
    if (isMatched(rouletteOutcome)) {
      new BetOutcome(this, WinBetOutcomeType, betType.getWinningsForBetAmount(amount))
    }
    else {
      new BetOutcome(this, LossBetOutcomeType, BigDecimal.apply(0))
    }
  }

  override def toString = s"Bet($player, $amount, $betType)"
}
