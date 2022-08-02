# Webflux Kafka Streams

Project to learn more about the Spring Cloud Functions and how to use it
to consume/produce Kafka messages in a functional and reactive way with Reactor and Kotlin Lambdas.

## How to run

With Docker installed, go to the root folder of this project and run the `docker-compose` file.

```
$ docker compose up -d
```

> Here I used the _compose_ plugin for Docker, so if you have _docker-compose_ itself installed, you 
> may need to use `docker-compose` command instead. 

This will up a Kafka broker for the tests in `localhost:9092` with two topics:
**quickstart** and **quickstart-response**.

Then, you can run the application in your IDE (_for now_) and start posting messages.

## How to post messages

I recommend you to keep two terminals open: one for producing messages on the **quickstart** topic,
and the other one to watch the output consuming the messages from **quickstart-response**.

To consume the output messages, run:

```
$ docker exec --interactive --tty broker \
  kafka-console-consumer --bootstrap-server broker:9092 \
                         --topic quickstart-response \
                         --from-beginning
```

And to start producing messages, run the following command:

```
$ docker exec --interactive --tty broker \
  kafka-console-producer --bootstrap-server broker:9092 \
                         --topic quickstart
```

It will keep the producer running, and everything you write will be sent as a Kafka message.

## How the project works

Once the Kafka broker and the application are up and running, it will convert every text you send to uppercase.

It's quite simple for now, but it's just to play with those concepts (it will get bigger soon ;) ).

## To do

- Create a Dockerfile for the application, so it will be running with `docker compose` command.
- Add more examples of producing and consuming messages from Kafka:
  - Producing messages from another source
  - Just consume messages
  - Object messages
  - Header conditions
  - Retry processing messages
  - Post to and consume from dead letter queue 
