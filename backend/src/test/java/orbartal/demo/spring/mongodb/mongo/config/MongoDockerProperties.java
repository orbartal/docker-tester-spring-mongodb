package orbartal.demo.spring.mongodb.mongo.config;

public class MongoDockerProperties {
	private int port = 27017;
	private String host = "localhost";
	private String dockerImage = "mongo:4.4.2";
	private String demoDb = "demodb";
	private String demoUserName = "demodb";
	private String demoUserPass = "demouser";
	private String MONGO_USER_PASS = "demopass";
	private String MONGO_USER_NAME = "demouser";

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDockerImage() {
		return dockerImage;
	}

	public void setDockerImage(String dockerImage) {
		this.dockerImage = dockerImage;
	}

	public String getDemoDb() {
		return demoDb;
	}

	public void setDemoDb(String demoDb) {
		this.demoDb = demoDb;
	}

	public String getDemoUserName() {
		return demoUserName;
	}

	public void setDemoUserName(String demoUserName) {
		this.demoUserName = demoUserName;
	}

	public String getDemoUserPass() {
		return demoUserPass;
	}

	public void setDemoUserPass(String demoUserPass) {
		this.demoUserPass = demoUserPass;
	}

	public String getMONGO_USER_PASS() {
		return MONGO_USER_PASS;
	}

	public void setMONGO_USER_PASS(String mONGO_USER_PASS) {
		MONGO_USER_PASS = mONGO_USER_PASS;
	}

	public String getMONGO_USER_NAME() {
		return MONGO_USER_NAME;
	}

	public void setMONGO_USER_NAME(String mONGO_USER_NAME) {
		MONGO_USER_NAME = mONGO_USER_NAME;
	}

}
