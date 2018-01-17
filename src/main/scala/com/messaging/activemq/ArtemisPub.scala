package com.messaging.activemq

import com.messaging.activemq.ArtemisSub.connection
import org.apache.qpid.jms.JmsConnectionFactory

object ArtemisPub extends App {
  import javax.jms.{Connection, Session, TextMessage}

  var connection: Connection = null
  val connectionFactory = new JmsConnectionFactory("amqp://localhost:5672")
  try { // Step 1. Create an amqp qpid 1.0 connection
    connection = connectionFactory.createConnection
    // Step 2. Create a session
    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    // Step 3. Create a sender
    val queue = session.createQueue("logs")
    val sender = session.createProducer(queue)
    // Step 4. send a few simple message
    sender.send(session.createTextMessage("Hello world of pub sub "))
    connection.start

  }  finally if (connection != null) { // Step 9. close the connection
    connection.close
  }
}
