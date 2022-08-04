package com.github.ftfetter.webfluxkafkastreams.messaging

import com.github.ftfetter.webfluxkafkastreams.messaging.KafkaProcessorIntegrationTest.Companion.INBOUND_TOPIC
import com.github.ftfetter.webfluxkafkastreams.messaging.KafkaProcessorIntegrationTest.Companion.OUTBOUND_TOPIC
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.TopicPartition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.annotation.DirtiesContext
import java.time.Duration
import java.time.temporal.ChronoUnit

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(topics = [INBOUND_TOPIC, OUTBOUND_TOPIC], partitions = 1)
internal class KafkaProcessorIntegrationTest {

    companion object{
        const val INBOUND_TOPIC = "quickstart"
        const val OUTBOUND_TOPIC = "quickstart-result"
        const val CONSUMER_GROUP = "test-consumer-group"
    }

    @Autowired
    lateinit var kafkaBroker: EmbeddedKafkaBroker

    lateinit var producer: KafkaProducer<Int, String>
    lateinit var consumer: KafkaConsumer<Int, String>

    @BeforeEach
    internal fun setUp() {
        producer = KafkaProducer(KafkaTestUtils.producerProps(kafkaBroker))
        consumer = KafkaConsumer(KafkaTestUtils.consumerProps(CONSUMER_GROUP, "false", kafkaBroker))
    }

    @Test
    internal fun `when the processor receives a string, then should return the uppercase`() {
        val data = "the quick brown fox jumps over the lazy dog"
        val outTopic = TopicPartition(OUTBOUND_TOPIC, 1)

        producer.send(ProducerRecord(INBOUND_TOPIC, data))
        consumer.assign(listOf(outTopic))

        consumer.poll(Duration.of(1, ChronoUnit.SECONDS))
            .records(outTopic)
            .forEach { assertEquals("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", it.value()) }
    }
}