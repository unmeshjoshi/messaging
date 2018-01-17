package com.messaging.activemq

import org.apache.qpid.jms.JmsConnectionFactory


object ArtemisSub extends App {

  import javax.jms.{Connection, Session, TextMessage}

  var connection: Connection = null
  val connectionFactory = new JmsConnectionFactory("amqp://localhost:5672")
  try { // Step 1. Create an amqp qpid 1.0 connection
    connection = connectionFactory.createConnection
    // Step 2. Create a session
    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    // Step 3. Create a sender
    val queue = session.createQueue("logs")

    connection.start
    // Step 5. create a moving receiver, this means the message will be removed from the queue
    val consumer = session.createConsumer(queue)
    // Step 7. receive the simple message
    val m = consumer.receive(5000).asInstanceOf[TextMessage]
    System.out.println("message = " + m.getText)
  } finally if (connection != null) { // Step 9. close the connection
    connection.close
  }
}