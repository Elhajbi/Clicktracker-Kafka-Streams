package ma.enset.clicktrackerkafkastreams.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClickCountStorage {

    // Stocke le nombre de clics par userId
    private final Map<String, Long> userClickCounts = new ConcurrentHashMap<>();

    // Met à jour le compteur de clics pour un utilisateur donné
    public void updateClickCount(String userId, Long count) {
        userClickCounts.put(userId, count);
    }

    // Retourne le total des clics cumulés de tous les utilisateurs
    public long getTotalClicks() {
        return userClickCounts.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    // Retourne la map complète des clics par utilisateur
    public Map<String, Long> getAllCounts() {
        return userClickCounts;
    }
}
