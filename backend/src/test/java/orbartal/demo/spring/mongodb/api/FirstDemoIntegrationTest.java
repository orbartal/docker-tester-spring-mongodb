package orbartal.demo.spring.mongodb.api;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import orbartal.demo.spring.mongodb.DemoSpringbootsMongoMain;
import orbartal.demo.spring.mongodb.mongo.container.MongoContainerUtil;

@SpringBootTest(classes = DemoSpringbootsMongoMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class FirstDemoIntegrationTest {
	
	@Container
	public static final GenericContainer<?> mongoDBContainer = MongoContainerUtil.buildMongoDbContainer();

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		MongoContainerUtil.setProperties(registry, mongoDBContainer);
	}

	@LocalServerPort
	private int port;

	@Test
	public void testGetAllDemo() throws Exception {
		String url = buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
	}

	private String buildUrlDemo() {
		return "http://localhost:" + port + "/api/demo";
	}

}