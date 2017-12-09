package com.roulette.exceptions

/**
  * Created by harshitha.suresh on 05/12/2017.
  */
case class UserInputException(private val message: String = "",
                           private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
