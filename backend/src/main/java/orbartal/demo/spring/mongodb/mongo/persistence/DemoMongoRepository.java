package orbartal.demo.spring.mongodb.mongo.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoMongoRepository extends MongoRepository<DemoMongoEntity, String> {

}