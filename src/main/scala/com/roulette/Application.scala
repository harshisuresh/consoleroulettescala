package com.roulette

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{Banner, CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by harshitha.suresh on 05/12/2017.
  */
@SpringBootApplication
class Application extends CommandLineRunner{
  @Autowired
  val commandLineRoulette : CommandLineRoulette = null
  override def run(args: String*): Unit = {
    val thread = commandLineRoulette.startGame
    thread.join()
  }
}
object Application  {
  def main(args: Array[String]) : Unit = {
    val app: SpringApplication = new SpringApplication(classOf[Application])
    app.setBannerMode(Banner.Mode.OFF)
    app.run(args: _ *)
    System.exit(0)
  }

}
