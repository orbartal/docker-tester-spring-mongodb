package orbartal.demo.spring.mongodb.tester.demo.util;

public class DemoUrlProvider {

	private final String demoUrl = DemoProptiesUtil.getDemoUrl();

	public String buildUrlDemo() {
		return demoUrl + "/api/demo";
	}

	public String buildUrlDemoDelete(String key) {
		return demoUrl + "/api/demo" + "/" + key;
	}

}
