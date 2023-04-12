package orbartal.demo.spring.mongodb.mongo.container;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.mongodb.client.MongoClient;

import orbartal.demo.spring.mongodb.mongo.persistence.DemoMongoBuilder;
import orbartal.demo.spring.mongodb.mongo.persistence.DemoMongoEntity;

@SpringBootTest
@Testcontainers
public class MongoClientTest {

	// The same container is shared by all tests
	@Container
	public static final GenericContainer<?> mongoDBContainer = MongoContainerUtil.buildMongoDbContainer();

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		MongoContainerUtil.setProperties(registry, mongoDBContainer);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoClient mongoClient;

	@Test
	public void testMongoClientConnectToDB() {
		List<String> dbNames = new ArrayList<>();
		mongoClient.listDatabases().forEach(d -> dbNames.add(d.getString("name")));
		Assertions.assertTrue(dbNames.contains("admin"));
	}

	@Test
	public void testMongoTemplateAndCollectionDemo() {
		Assertions.assertEquals(0, mongoTemplate.getCollectionNames().size());
		forceInitCollectionByAddingDemoEntity();
		Assertions.assertEquals(1, mongoTemplate.getCollectionNames().size());
		String collectionName = mongoTemplate.getCollectionNames().iterator().next();
		Assertions.assertEquals("demo", collectionName);
	}

	private void forceInitCollectionByAddingDemoEntity() {
		String uuid = UUID.randomUUID().toString();
		DemoMongoEntity demo = new DemoMongoBuilder().uuid(uuid).value("va1").build();
		this.mongoTemplate.save(demo);
	}

}