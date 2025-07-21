package ma.enset.clicktrackerkafkastreams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class ClicktrackerKafkaStreams {

    public static void main(String[] args) {
        SpringApplication.run(ClicktrackerKafkaStreams.class, args);
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {
        // Spécifie bien les Serdes pour la lecture
        KStream<String, String> stream = builder.stream("clicks", Consumed.with(Serdes.String(), Serdes.String()));

        stream
                // Spécifie aussi les Serdes pour le groupByKey
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .count(Materialized.as("clicks-store"))
                .toStream()
                // Convertit le Long en String pour envoyer dans le topic
                .map((key, count) -> new KeyValue<>(key, count.toString()))
                // Spécifie les Serdes pour la production
                .to("click-counts", Produced.with(Serdes.String(), Serdes.String()));

        return stream;
    }
}
