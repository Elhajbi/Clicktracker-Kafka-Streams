package ma.enset.clicktrackerkafkastreams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClickProducer {

    private static final String TOPIC = "clicks";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Envoie un message "click" avec la clé userId au topic "clicks"
    public void sendClick(String userId) {
        kafkaTemplate.send(TOPIC, userId, "click");
    }
}

