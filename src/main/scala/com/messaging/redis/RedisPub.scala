package com.messaging.redis

import redis.clients.jedis.Jedis

object RedisPub extends App {
  val jedis = new Jedis("localhost")
  val channel = "logs"
  System.out.println("Starting publisher for channel " + channel)
  jedis.publish(channel, "hello")
  jedis.publish(channel, "hello1")
}

