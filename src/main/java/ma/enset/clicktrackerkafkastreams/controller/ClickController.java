package ma.enset.clicktrackerkafkastreams.controller;

import ma.enset.clicktrackerkafkastreams.service.ClickProducer;
import ma.enset.clicktrackerkafkastreams.service.ClickCountStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clicks")
public class ClickController {

    @Autowired
    private ClickProducer clickProducer;

    @Autowired
    private ClickCountStorage clickCountStorage;

    @PostMapping
    public String sendClick(@RequestParam String userId) {
        clickProducer.sendClick(userId);
        return "✅ Click sent for user: " + userId;
    }

    @GetMapping("/count")
    public long getTotalClicks() {
        return clickCountStorage.getTotalClicks();
    }

    @GetMapping("/count/all")
    public Map<String, Long> getAllUserClicks() {
        return clickCountStorage.getAllCounts();
    }
}
