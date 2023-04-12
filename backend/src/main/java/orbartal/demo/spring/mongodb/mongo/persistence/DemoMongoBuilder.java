package orbartal.demo.spring.mongodb.mongo.persistence;

public class DemoMongoBuilder {

	private String uuid;
	private String value;

	public DemoMongoBuilder uuid(String name1) {
		this.uuid = name1;
		return this;
	}

	public DemoMongoBuilder value(String value1) {
		this.value = value1;
		return this;
	}

	public DemoMongoEntity build() {
		return new DemoMongoEntity(uuid, value);
	}

}
