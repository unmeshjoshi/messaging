package com.messaging.rabbitmq

object RabbitMQPub extends App {

  import com.rabbitmq.client.ConnectionFactory

  private val EXCHANGE_NAME = "logs"
  val factory = new ConnectionFactory
  factory.setHost("localhost")
  factory.setPort(5672)
  val connection = factory.newConnection
  val channel = connection.createChannel
  channel.exchangeDeclare(EXCHANGE_NAME, "fanout")
  val message = "Hello RabbitMQ!"
  channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes)
  System.out.println(" [x] Sent '" + message + "'")
  channel.close()
  connection.close()

}
