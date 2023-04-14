package orbartal.demo.spring.mongodb.mongo.container;

import java.util.List;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoContainerUtil {

	private static final String MONGO_IMAGE = "mongo:4.4.2";
	private static final String MONGO_DB_NAME = "demodb";
	private static final int MONGO_PORT = 27017;
	private static final String MONGO_USER_PASS = "demopass";
	private static final String MONGO_USER_NAME = "demouser";
	
	private static final DockerImageName MONGO_DOCKER_IMAGE = DockerImageName.parse(MONGO_IMAGE);

	@SuppressWarnings("resource")
	public static MongoDBContainer buildMongoDbContainer() {
		return new MongoDBContainer(MONGO_DOCKER_IMAGE)
		.withEnv("MONGO_INITDB_DATABASE", MONGO_DB_NAME);
	}

	public static void setProperties(DynamicPropertyRegistry registry, GenericContainer<?> mongoDBContainer) {
		String host = mongoDBContainer.getHost();
		int mapPort0 = getMongoPort(mongoDBContainer);
		registry.add("spring.data.mongodb.host", ()->host);
		registry.add("spring.data.mongodb.port", ()->mapPort0);
		registry.add("spring.data.mongodb.database", ()->MONGO_DB_NAME);
	}
	
	@SuppressWarnings("resource")
	public static GenericContainer<?> buildMongoDbContainerWithSecurity() {
		return new GenericContainer<>
				(DockerImageName.parse(MONGO_IMAGE))
				.withEnv("MONGO_INITDB_ROOT_USERNAME", MONGO_USER_NAME)
				.withEnv("MONGO_INITDB_ROOT_PASSWORD", MONGO_USER_PASS)
				.withEnv("MONGO_INITDB_DATABASE", MONGO_DB_NAME)
				.withExposedPorts(MONGO_PORT);
	}
	
	public static void setPropertiesWithSecurity(DynamicPropertyRegistry registry, GenericContainer<?> mongoDBContainer) {
		String host = mongoDBContainer.getHost();
		int mapPort0 = getMongoPort(mongoDBContainer);
		registry.add("spring.data.mongodb.host", ()->host);
		registry.add("spring.data.mongodb.port", ()->mapPort0);
		registry.add("spring.data.mongodb.database", ()->MONGO_DB_NAME);
		registry.add("spring.data.mongodb.username", ()->MONGO_USER_NAME);
		registry.add("spring.data.mongodb.password", ()->MONGO_USER_PASS);
		registry.add("spring.data.mongodb.authentication-database", ()->"admin");
	}

	private static int getMongoPort(GenericContainer<?> mongoDBContainer) {
		List<Integer> ports = mongoDBContainer.getExposedPorts();
		int port0 = ports.get(0);
		return mongoDBContainer.getMappedPort(port0);
	}

}
