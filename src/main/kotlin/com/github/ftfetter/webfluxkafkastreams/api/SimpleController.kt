package com.github.ftfetter.webfluxkafkastreams.api

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SimpleController(val bridge: StreamBridge) {

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun postMessage(@RequestBody s: String) = Mono.just(s)
        .map { bridge.send("sendMessage-out-0", it) }
        .then()
}