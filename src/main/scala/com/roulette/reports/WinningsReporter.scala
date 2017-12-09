package com.roulette.reports

import com.roulette.bet.BetOutcome
import org.springframework.stereotype.Service

/**
  * Created by harshitha.suresh on 05/12/2017.
  */
@Service
class WinningsReporter extends Reporter{
  override def onBetOutcomes(rouletteOutcome: Int, betOutcomes: List[BetOutcome]): Unit = {
    System.out.println("rouletteOutcome: " + rouletteOutcome)
    System.out.println("Player       Bet     Outcome    Winnings ")
    System.out.println("-------------------------------------------")
    for (betOutcome <- betOutcomes) {
      System.out.format("%s %s %s %s\n", betOutcome.bet.player.name,
        betOutcome.bet.betType.asString, betOutcome.betOutcomeType, betOutcome.winningsAmount)
    }
  }
}
