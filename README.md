# Kafka Tutorial

This tutorial will guide you on how to download Kafka binaries and start the servers and topics.

## Prerequisites

Before getting started, make sure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Apache Kafka binaries

## Download Kafka Binaries

To download Kafka binaries, follow these steps:

1. Go to the [Apache Kafka website](https://kafka.apache.org/downloads).
2. Select the desired Kafka version and click on the download link.
3. Choose the appropriate binary package for your operating system.
4. Once downloaded, extract the contents of the package to a directory of your choice.

## Start Kafka Servers

To start Kafka servers, follow these steps:

1. Open a terminal and navigate to the Kafka installation directory.
2. Start the ZooKeeper server by running the following command:
  ```
  bin/zookeeper-server-start.sh config/zookeeper.properties
  ```
3. In a new terminal, navigate to the Kafka installation directory.
4. Start the Kafka server by running the following command:
  ```
  bin/kafka-server-start.sh config/server.properties
  ```

## Create Kafka Topics

To create Kafka topics, follow these steps:

1. Open a terminal and navigate to the Kafka installation directory.
2. Run the following command to create a topic named "my-topic":
  ```
  bin/kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
  ```

That's it! You have successfully downloaded Kafka binaries and started the servers and topics. Happy Kafka-ing!