package com.roulette.bet

import com.roulette.player.Player
import org.mockito.Mockito._
import org.scalatest.FunSuite

/**
  * Created by harshitha.suresh on 07/12/2017.
  */
class BetTest extends FunSuite{

  def createBet(betType: BetType, betAmount: BigDecimal): Bet = { new Bet( new Player("player"), betAmount, betType) }

  test("computeBetOutcomeReturnsWinBetOutComeTypeWhenBetIsMatched") {
    //setup
    val rouletteOutcome: Int = 1
    val betType: BetType = mock(classOf[BetType])
    when(betType.isMatched(rouletteOutcome)).thenReturn(true)
    val betAmount: BigDecimal = BigDecimal.apply(1)
    val winnings: BigDecimal = BigDecimal.apply(2)
    when(betType.getWinningsForBetAmount(betAmount)).thenReturn(winnings)
    val bet: Bet = createBet(betType, betAmount)
    // act
    val betOutcome: BetOutcome = bet.computeBetOutcome(rouletteOutcome)
    //assert
    assert(betOutcome.betOutcomeType == WinBetOutcomeType)
  }

  test("computeBetOutcomeReturnsLossBetOutComeTypeWhenBetIsNotMatched") {
    //setup
    val rouletteOutcome: Int = 1
    val betType: BetType = mock(classOf[BetType])
    when(betType.isMatched(rouletteOutcome)).thenReturn(false)
    val betAmount: BigDecimal = BigDecimal.apply(1)
    val winnings: BigDecimal = BigDecimal.apply(0)
    val bet: Bet = createBet(betType, betAmount)
    // act
    val betOutcome: BetOutcome = bet.computeBetOutcome(rouletteOutcome)
    //assert
    assert(betOutcome.betOutcomeType == LossBetOutcomeType)
  }
}
