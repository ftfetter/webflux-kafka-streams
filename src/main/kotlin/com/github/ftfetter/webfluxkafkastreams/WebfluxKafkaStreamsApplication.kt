package com.github.ftfetter.webfluxkafkastreams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxKafkaStreamsApplication

fun main(args: Array<String>) {
	runApplication<WebfluxKafkaStreamsApplication>(*args)
}
