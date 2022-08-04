# Webflux Kafka Streams

Project to learn more about the Spring Cloud Functions and how to use it
to consume/produce Kafka messages in a functional and reactive way with Reactor and Kotlin Lambdas.

## How to run

With Docker installed, go to the root folder of this project and run the `kafka-setup` script.

```shell
$ ./kafka-setup.sh
```

This will up a Zookeeper instance and a Kafka broker for the tests in `localhost:9092` with two topics:
**quickstart** and **quickstart-response**.

Then, you can run the application in your IDE (_for now_) and start posting messages.

## How to consume messages

To consume the output messages, run:

```shell
$ docker exec --interactive --tty broker \
  kafka-console-consumer --bootstrap-server broker:9092 \
                         --topic quickstart-response \
                         --from-beginning
```

## How to post messages

### Directly into Kafka broker

To produce messages directly, run:

```shell
$ docker exec --interactive --tty broker \
  kafka-console-producer --bootstrap-server broker:9092 \
                         --topic quickstart
```

It will keep the producer running, and everything you write will be sent as a Kafka message.

### Via REST API

Another way to produce messages is by REST API. You can run the following command: 

```shell
$ curl --location --request POST 'localhost:8080/post' \
  --header 'Content-Type: text/plain' \
  --data-raw 'hello world!'
```

This will post a message just like the body sent.
In this case, will post `hello world!` into **quickstart-response**.

Note that this message is not converted to uppercase. It's posted as received.

## How the project works

Once the Kafka broker and the application are up and running, it will convert every text you send to uppercase.

It's quite simple for now, but it's just to play with those concepts (it will get bigger soon ;) ).

## To do

- Create a Dockerfile for the application, so it will be running with `docker compose` command.
- Add more examples of producing and consuming messages from Kafka:
  - ~~Producing messages from another source~~
  - Just consume messages
  - Object messages
  - Header conditions
  - Retry processing messages
  - Post to and consume from dead letter queue 
