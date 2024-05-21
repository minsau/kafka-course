# Set the Kafka home directory
KAFKA_HOME := /Users/saul.gomez/tools/kafka
# Define common commands
.PHONY: start stop create-topic produce consume
start:
	$(KAFKA_HOME)/bin/zookeeper-server-start.sh $(KAFKA_HOME)/config/zookeeper.properties &
	$(KAFKA_HOME)/bin/kafka-server-start.sh $(KAFKA_HOME)/config/server.properties &

stop:
	$(KAFKA_HOME)/bin/kafka-server-stop.sh
	$(KAFKA_HOME)/bin/zookeeper-server-stop.sh

create-topic:
	$(KAFKA_HOME)/bin/kafka-topics.sh --create --topic $(topic) --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

produce:
	$(KAFKA_HOME)/bin/kafka-console-producer.sh --topic $(topic) --bootstrap-server localhost:9092

consume:
	$(KAFKA_HOME)/bin/kafka-console-consumer.sh --topic $(topic) --bootstrap-server localhost:9092 --from-beginning