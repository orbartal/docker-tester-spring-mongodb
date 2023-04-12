package orbartal.demo.spring.mongodb.tester.demo.util;

import orbartal.demo.spring.mongodb.tester.demo.DemoDto;

public class DemoDtoFactory {

	static public DemoDto buildDemoDto(String key, String value) {
		DemoDto entity = new DemoDto();
		entity.setUuid(key);
		entity.setValue(value);
		return entity;
	}

}
