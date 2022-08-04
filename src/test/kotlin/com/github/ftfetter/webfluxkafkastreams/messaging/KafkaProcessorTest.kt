package com.github.ftfetter.webfluxkafkastreams.messaging

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

internal class KafkaProcessorTest{

    private val processor = KafkaProcessor()

    @Test
    fun `valid input must return a valid output`() {
        val asset = Flux.just("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog")

        val actual = processor.readAndSendMessage().invoke(asset)

        StepVerifier.create(actual)
            .assertNext { assertEquals("THE", it) }
            .assertNext { assertEquals("QUICK", it) }
            .assertNext { assertEquals("BROWN", it) }
            .assertNext { assertEquals("FOX", it) }
            .assertNext { assertEquals("JUMPS", it) }
            .assertNext { assertEquals("OVER", it) }
            .assertNext { assertEquals("THE", it) }
            .assertNext { assertEquals("LAZY", it) }
            .assertNext { assertEquals("DOG", it) }
            .verifyComplete()
    }

}