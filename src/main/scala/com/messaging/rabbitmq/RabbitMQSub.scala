package com.messaging.rabbitmq

import java.io.IOException

import com.rabbitmq.client.{AMQP, ConnectionFactory, DefaultConsumer, _}


object RabbitMQSub extends App {
  private val EXCHANGE_NAME = "logs"

  val factory = new ConnectionFactory
  factory.setHost("localhost")
  val connection = factory.newConnection
  val channel = connection.createChannel
  channel.exchangeDeclare(EXCHANGE_NAME, "fanout")
  val queueName = channel.queueDeclare.getQueue
  channel.queueBind(queueName, EXCHANGE_NAME, "")
  System.out.println(" [*] Waiting for messages. To exit press CTRL+C")
  val consumer = new DefaultConsumer(channel) {
    @throws[IOException]
    override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
      val message = new String(body, "UTF-8")
      System.out.println(" [x] Received '" + message + "'")
    }
  }
  channel.basicConsume(queueName, true, consumer)
}