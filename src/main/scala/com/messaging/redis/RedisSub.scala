package com.messaging.redis

import redis.clients.jedis.{Jedis, JedisPubSub}

object RedisSub extends App {

  val channel = "logs"
  val jedis = new Jedis("localhost")
  jedis.subscribe(new JedisPubSub() {
    override def onMessage(channel: String, message: String): Unit = {
      super.onMessage(channel, message)
      System.out.println("Received message:" + message)
    }

    override

    def onSubscribe(channel: String, subscribedChannels: Int): Unit = {
    }

    override

    def onUnsubscribe(channel: String, subscribedChannels: Int): Unit = {
    }

    override

    def onPMessage(pattern: String, channel: String, message: String): Unit = {
    }

    override

    def onPUnsubscribe(pattern: String, subscribedChannels: Int): Unit = {
    }

    override

    def onPSubscribe(pattern: String, subscribedChannels: Int): Unit = {
    }
  }, channel)
}