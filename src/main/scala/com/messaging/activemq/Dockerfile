FROM openjdk:alpine

ENV ACTIVEMQ_ARTEMIS_VERSION 2.4.0

RUN apk update && apk add wget

RUN mkdir /opt && cd /opt && wget -q https://repository.apache.org/content/repositories/releases/org/apache/activemq/apache-artemis/${ACTIVEMQ_ARTEMIS_VERSION}/apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION}-bin.tar.gz && \
  wget -q https://repository.apache.org/content/repositories/releases/org/apache/activemq/apache-artemis/${ACTIVEMQ_ARTEMIS_VERSION}/apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION}-bin.tar.gz.asc && \
  wget -q http://apache.org/dist/activemq/KEYS && \
  tar xfz apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION}-bin.tar.gz && \
  ln -s apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION} apache-artemis && \
  rm -f apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION}-bin.tar.gz KEYS apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION}-bin.tar.gz.asc

# Create broker instance
RUN cd /var/lib && \
  /opt/apache-artemis-${ACTIVEMQ_ARTEMIS_VERSION}/bin/artemis create artemis --user root --password password --allow-anonymous

# Web Server
EXPOSE 8161

# Port for CORE,MQTT,AMQP,HORNETQ,STOMP,OPENWIRE
EXPOSE 61616

# Port for HORNETQ,STOMP
EXPOSE 5445

# Port for AMQP
EXPOSE 5672

# Port for MQTT
EXPOSE 1883

#Port for STOMP
EXPOSE 61613

# Expose some outstanding folders
VOLUME ["/var/lib/artemis/data"]
VOLUME ["/var/lib/artemis/tmp"]
VOLUME ["/var/lib/artemis/etc"]
VOLUME ["/var/lib/artemis/etc-override"]

COPY broker.xml /var/lib/artemis/etc
WORKDIR /var/lib/artemis/bin
CMD ["./artemis", "run"]
