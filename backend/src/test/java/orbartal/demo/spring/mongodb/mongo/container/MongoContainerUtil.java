package orbartal.demo.spring.mongodb.mongo.container;

import java.util.List;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import orbartal.demo.spring.mongodb.mongo.config.MongoDockerProperties;

public class MongoContainerUtil {

	private static final MongoDockerProperties properties = MongoDockerProperties.getIntance();

	@SuppressWarnings("resource")
	public static MongoDBContainer buildMongoDbContainer() {
		String demoDb = properties.getDemoDb();
		String dockerImage = properties.getDockerImage();
		DockerImageName image = DockerImageName.parse(dockerImage);
		return new MongoDBContainer(image).withEnv("MONGO_INITDB_DATABASE", demoDb);
	}

	public static void setProperties(DynamicPropertyRegistry registry, GenericContainer<?> mongoDBContainer) {
		String host = mongoDBContainer.getHost();
		int mapPort0 = getMongoPort(mongoDBContainer);
		registry.add("spring.data.mongodb.host", ()->host);
		registry.add("spring.data.mongodb.port", ()->mapPort0);
		registry.add("spring.data.mongodb.database", ()->properties.getDemoDb());
	}
	
	@SuppressWarnings("resource")
	public static GenericContainer<?> buildMongoDbContainerWithSecurity() {
		return new GenericContainer<>
				(DockerImageName.parse(properties.getDockerImage()))
				.withEnv("MONGO_INITDB_ROOT_USERNAME", properties.getDemoUserName())
				.withEnv("MONGO_INITDB_ROOT_PASSWORD", properties.getDemoUserPass())
				.withEnv("MONGO_INITDB_DATABASE", properties.getDemoDb())
				.withExposedPorts(properties.getPort());
	}
	
	public static void setPropertiesWithSecurity(DynamicPropertyRegistry registry, GenericContainer<?> mongoDBContainer) {
		String host = mongoDBContainer.getHost();
		int mapPort0 = getMongoPort(mongoDBContainer);
		registry.add("spring.data.mongodb.host", ()->host);
		registry.add("spring.data.mongodb.port", ()->mapPort0);
		registry.add("spring.data.mongodb.database", ()->properties.getDemoDb());
		registry.add("spring.data.mongodb.username", ()->properties.getDemoUserName());
		registry.add("spring.data.mongodb.password", ()->properties.getDemoUserPass());
		registry.add("spring.data.mongodb.authentication-database", ()->"admin");
	}

	private static int getMongoPort(GenericContainer<?> mongoDBContainer) {
		List<Integer> ports = mongoDBContainer.getExposedPorts();
		int port0 = ports.get(0);
		return mongoDBContainer.getMappedPort(port0);
	}

}
