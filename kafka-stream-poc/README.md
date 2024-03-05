## Build

### Start kafka

go to `kafka-producer` service (upper level) and follow their instructions

- start dockered kafka
- register schemas in the schema registry
- start `kafka-producer` app with `avro` profile to emulate the data publishing
- back to current applicaiton and build one.

### Build and start the app

```
mvn clean install
```

start it with profile `78107151` (`spring.profiles.active=78107151`)

### Steps to reproduce:

- go to `kafka-producer` (project located 1 level upper)
- `docker-compose up -d`
- `mvn schema-registry:register`
- start `kafka-producer` with profile `experimental-avro` (`spring.profiles.active=experimental-avro`) (it will produce every 15 seconds message to `report_topic` topic serialized by `avro`)
- navigate back to the `kafka-stream-poc`
- start the `kafka-stream-poc` with profile `78107151` (`spring.profiles.active=78107151`)
- in logs you'll see every 15 seconds random `report` message
- to monitor dlq messages, please connect your consumer to `report_dlq` in terminal

```
docker exec -it broker kafka-console-consumer --bootstrap-server broker:9092 --topic report_dlq --from-beginning
```

- connect with producer to `report_topic` in different terminal

```
docker exec -it broker kafka-console-producer --bootstrap-server broker:9092 --topic report_topic
```

- to reproduce issue just put any string in producer terminal to publish messages to input stream (`report_topic`)
- check logs in `kafka-stream-poc` (it will have an exception)
- check the `DQL` (`report_dlq`) - there will be nothing.

### Check in kafka

#### Producers

```
docker exec -it broker kafka-console-producer --bootstrap-server broker:9092 --topic report_topic
```

#### Consumers

```
docker exec -it broker kafka-console-consumer --bootstrap-server broker:9092 --topic report_topic --from-beginning
docker exec -it broker kafka-console-consumer --bootstrap-server broker:9092 --topic report_topic_redirect --from-beginning
docker exec -it broker kafka-console-consumer --bootstrap-server broker:9092 --topic report_dlq --from-beginning
```
