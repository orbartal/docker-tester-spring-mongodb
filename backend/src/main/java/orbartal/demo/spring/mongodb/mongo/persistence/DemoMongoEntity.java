package orbartal.demo.spring.mongodb.mongo.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "demo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoMongoEntity {

    @Id
    private String uuid;

    @Field
    private String value;

	public DemoMongoEntity() {}

	public DemoMongoEntity(String uuid1, String value) {
		this.uuid = uuid1;
		this.value = value;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid1) {
		this.uuid = uuid1;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value1) {
		this.value = value1;
	}

}
