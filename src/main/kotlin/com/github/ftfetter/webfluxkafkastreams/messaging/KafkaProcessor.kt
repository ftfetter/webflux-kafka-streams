package com.github.ftfetter.webfluxkafkastreams.messaging

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class KafkaProcessor {

    @Bean
    fun readAndSendMessage(): (Flux<String>) -> Flux<String> {
        return { flux ->
            flux.map { it.uppercase() }
                .doOnNext { println(it) }
        }
    }
}