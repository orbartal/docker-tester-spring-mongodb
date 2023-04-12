package orbartal.demo.spring.mongodb.mongo.persistence;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import orbartal.demo.spring.mongodb.mongo.container.MongoContainerUtil;

@SpringBootTest
@Testcontainers
public class DemoMongoRepositoryTest {

	static private final String UUID_1 = UUID.randomUUID().toString();
	static private final String UUID_2 = UUID.randomUUID().toString();
	static private final String VALUE_11 = "va1";
	static private final String VALUE_12 = "va2";
	static private final String VALUE_21 = "vb1";

	@Container
	public static final GenericContainer<?> mongoDBContainer = MongoContainerUtil.buildMongoDbContainer();

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		MongoContainerUtil.setProperties(registry, mongoDBContainer);
	}

	@Autowired 
	private DemoMongoRepository demoRepository;

    @BeforeEach
    public void init() {
    	demoRepository.deleteAll();
    }

	@Test
	void testCreateAndCount() {
		Assertions.assertEquals(0L, demoRepository.count());
		DemoMongoEntity user = new DemoMongoBuilder().uuid(UUID_1).value(VALUE_11).build();
		demoRepository.save(user);
		Assertions.assertEquals(1L, demoRepository.count());
		DemoMongoEntity user2 = new DemoMongoBuilder().uuid(UUID_2).value(VALUE_21).build();
		demoRepository.save(user2);
		Assertions.assertEquals(2L, demoRepository.count());
	}
	
	@Test
	void testCreateDeleteAndCount() {
		Assertions.assertEquals(0L, demoRepository.count());
		DemoMongoEntity user = new DemoMongoBuilder().uuid(UUID_1).value(VALUE_11).build();
		demoRepository.save(user);
		Assertions.assertEquals(1L, demoRepository.count());
		DemoMongoEntity user2 = new DemoMongoBuilder().uuid(UUID_2).value(VALUE_21).build();
		demoRepository.save(user2);
		Assertions.assertEquals(2L, demoRepository.count());
		demoRepository.delete(user2);
		Assertions.assertEquals(1L, demoRepository.count());
	}

	@Test
	void testCreateAndFindByUid() {
		Assertions.assertEquals(0L, demoRepository.count());
		DemoMongoEntity user = new DemoMongoBuilder().uuid(UUID_1).value(VALUE_11).build();
		demoRepository.save(user);
		Assertions.assertEquals(1L, demoRepository.count());

		Optional<DemoMongoEntity> puser2 = demoRepository.findById(UUID_1.toString());
		Assertions.assertNotNull(puser2);
		Assertions.assertTrue(puser2.isPresent());
		DemoMongoEntity user2 = puser2.get();
		Assertions.assertEquals(UUID_1, user2.getUuid());
		Assertions.assertEquals(VALUE_11, user2.getValue());
	}

	@Test
	void testCreateAndUpdate() {
		Assertions.assertEquals(0L, demoRepository.count());
		DemoMongoEntity user = new DemoMongoBuilder().uuid(UUID_1).value(VALUE_11).build();
		demoRepository.save(user);
		Assertions.assertEquals(1L, demoRepository.count());

		Optional<DemoMongoEntity> puser2 = demoRepository.findById(UUID_1.toString());
		Assertions.assertNotNull(puser2);
		Assertions.assertTrue(puser2.isPresent());
		DemoMongoEntity user2 = puser2.get();
		Assertions.assertEquals(UUID_1, user2.getUuid());
		Assertions.assertEquals(VALUE_11, user2.getValue());

		user2.setValue(VALUE_12);
		demoRepository.save(user2);
		Optional<DemoMongoEntity> puser3 = demoRepository.findById(UUID_1.toString());
		Assertions.assertNotNull(puser3);
		Assertions.assertTrue(puser3.isPresent());
		DemoMongoEntity user3 = puser3.get();
		Assertions.assertEquals(UUID_1, user3.getUuid());
		Assertions.assertEquals(VALUE_12, user3.getValue());
	}
}