package com.roulette

import java.security.SecureRandom
import java.util
import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue, Executors, TimeUnit}

import com.roulette.bet.{Bet, BetOutcome, BetType}
import com.roulette.player.Player
import com.roulette.reports.Reporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

/**
  * Created by harshitha.suresh on 04/12/2017.
  */

// Is there a better way to do this in Scala
@Service
class CometRouletteEngine extends RouletteEngine {
  @Autowired
  val reporter : Reporter = null
  private var bets: BlockingQueue[Bet] = new ArrayBlockingQueue[Bet](1000)
  private val rand: SecureRandom = new SecureRandom
  private var playerBetOutcomes = scala.collection.mutable.Map[Player, List[BetOutcome]]()

  scheduleRandomNumberGeneration()

  def placeBet(bet: Bet) {
    try {
      bets.put(bet)
    }
    catch {
      case e: InterruptedException => throw new RuntimeException("Unable to place bet: " + bet)
    }
  }


  def rouletteLanded(rouletteOutcome: Int) {
    val placedBets: util.ArrayList[Bet] = new util.ArrayList[Bet]
    bets.drainTo(placedBets)
    val scalaBets = placedBets.asScala
    val betOutcomeList: List[BetOutcome] = scalaBets.toStream.map(_.computeBetOutcome(rouletteOutcome)).toList
    betOutcomeList.foreach(this.updatePlayerBetOutcomes)
    reporter.onBetOutcomes(rouletteOutcome, betOutcomeList)
  }

  private def updatePlayerBetOutcomes(bo: BetOutcome) {
    playerBetOutcomes.getOrElseUpdate(bo.bet.player, List()).:+(bo)
  }

  // Is there a better way to do this in Scala
  private def scheduleRandomNumberGeneration() {
    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate( () => rouletteLanded(rand.nextInt(BetType.ROULETTE_SLOTS.toInt + 1)), 30, 30, TimeUnit.SECONDS)
  }
}
