package com.roulette.reports

import com.roulette.bet.BetOutcome

/**
  * Created by harshitha.suresh on 05/12/2017.
  */
trait Reporter {

  def onBetOutcomes(rouletteOutcome : Int, betOutcomes : List[BetOutcome])

}
