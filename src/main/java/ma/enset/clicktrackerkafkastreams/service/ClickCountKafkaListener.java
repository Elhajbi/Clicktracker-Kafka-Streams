package ma.enset.clicktrackerkafkastreams.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClickCountKafkaListener {

    @Autowired
    private ma.enset.clicktrackerkafkastreams.service.ClickCountStorage storage;
    @KafkaListener(topics = "click-counts", groupId = "click-counter-api")
    public void listen(ConsumerRecord<String, String> record) {
        String userId = record.key();
        String value = record.value();

        try {
            long count = Long.parseLong(value);
            storage.updateClickCount(userId, count);
        } catch (NumberFormatException e) {
            System.err.println("Invalid count value received: " + value);
        }
    }
}
