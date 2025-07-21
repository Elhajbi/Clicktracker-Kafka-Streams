# Clicktracker Kafka Streams

## Description

Application Spring Boot utilisant Kafka et Kafka Streams pour compter en temps réel les clics des utilisateurs.

- **POST /clicks?userId=xxx** : Envoie un clic pour un utilisateur donné.
- **GET /clicks/count** : Récupère le nombre total de clics de tous les utilisateurs.
- **GET /clicks/count/all** : Récupère le nombre de clics par utilisateur.

---

## Fonctionnement

1. Le service produit des messages Kafka sur le topic `clicks` avec la clé `userId`.
2. Kafka Streams agrège en temps réel le nombre de clics par utilisateur.
3. Le résultat est envoyé dans le topic `click-counts`.
4. Un listener Kafka consomme `click-counts` pour stocker les résultats en mémoire.
5. L’API REST expose ces données pour consultation.

---

## Prérequis

- Java 17
- Maven
- Docker et Docker Compose
- Kafka et Zookeeper (fournis via Docker Compose)

---

## Démarrage

1. Démarrer Kafka et Zookeeper via Docker Compose :

```bash
docker-compose up -d
```
2. Créer les topics nécessaires :
```bash
docker exec -it kafka-tools kafka-topics --create --topic clicks --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
docker exec -it kafka-tools kafka-topics --create --topic click-counts --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
```
3. Lancer l’application Spring Boot :
```bash
mvn spring-boot:run
```
## Utilisation
- Envoyer un clic pour l’utilisateur karim :
```bash
curl -X POST "http://localhost:8080/clicks?userId=karim"
```
- Obtenir le total des clics :
```bash
curl http://localhost:8080/clicks/count
```
- Obtenir les clics par utilisateur :
```bash
curl http://localhost:8080/clicks/count/all
```
## Technologies utilisées

- Spring Boot 3.5.3
- Kafka 3.7.0
- Kafka Streams
- Docker (Kafka, Zookeeper)

---

## Auteur

Abdelkarim El Hajbi – Full Stack Developer  
[LinkedIn](https://www.linkedin.com/in/abdelkarim-el-hajbi) | [GitHub](https://github.com/abdelkarimelhajbi)

