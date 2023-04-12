package orbartal.demo.spring.mongodb.data;

import java.util.UUID;

import org.springframework.stereotype.Component;

import orbartal.demo.spring.mongodb.business.DemoInfo;
import orbartal.demo.spring.mongodb.mongo.persistence.DemoMongoEntity;

@Component
public class DemoEntityInfoMapper {

	public DemoMongoEntity toEntity(DemoInfo input) {
		DemoMongoEntity out = new DemoMongoEntity();
		out.setUuid(input.getUid().toString());
		out.setValue(input.getValue());
		return out;
	}

	public DemoInfo toInfo(DemoMongoEntity input) {
		UUID uid = UUID.fromString(input.getUuid());
		return new DemoInfo(uid, input.getValue());
	}

}
