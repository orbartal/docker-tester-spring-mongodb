package orbartal.demo.spring.mongodb.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import orbartal.demo.spring.mongodb.business.DemoInfo;
import orbartal.demo.spring.mongodb.mongo.persistence.DemoMongoEntity;
import orbartal.demo.spring.mongodb.mongo.persistence.DemoMongoRepository;

@Transactional
@Component
public class DemoData {
	
	@Autowired
	private DemoEntityInfoMapper mapper;

	@Autowired
	private DemoMongoRepository demoRepository;

	static final Comparator<DemoInfo> uidComparator = (a, b) -> a.getUid().toString().compareTo(b.getUid().toString());

	@Transactional(readOnly = true)
	public synchronized List<DemoInfo> readAll() {
		List<DemoMongoEntity> entities = new ArrayList<>();
		demoRepository.findAll().forEach(entities::add);
		return entities.stream().map(e -> mapper.toInfo(e)).sorted(uidComparator).toList();
	}

	@Transactional(readOnly = true)
	public synchronized Optional<DemoInfo> readByUid(UUID uid) {
		Optional<DemoMongoEntity> entitiy = demoRepository.findById(uid.toString());
		return (entitiy != null) ? entitiy.map(e -> mapper.toInfo(e)) : Optional.empty();
	}

	@Transactional(readOnly = false)
	public synchronized DemoInfo create(DemoInfo input) {
		DemoMongoEntity newEntitiy = mapper.toEntity(input);
		Optional<DemoMongoEntity> pOldEntitiy = demoRepository.findById(newEntitiy.getUuid());
		if (pOldEntitiy != null && pOldEntitiy.isPresent()) {
			throw new RuntimeException("Duplicate uuid: " + input.getUid());
		}
		demoRepository.save(newEntitiy);
		return mapper.toInfo(newEntitiy);
	}

	@Transactional(readOnly = false)
	public synchronized DemoInfo update(DemoInfo input) {
		String value = input.getValue();
		UUID uuid = input.getUid();
		Optional<DemoMongoEntity> pEntitiy = demoRepository.findById(uuid.toString());
		if (pEntitiy == null || pEntitiy.isEmpty()) {
			throw new RuntimeException("Missing uuid: " + input.getUid());
		}
		DemoMongoEntity entity = pEntitiy.get();
		entity.setValue(value);
		demoRepository.save(entity);
		return mapper.toInfo(entity);
	}

	@Transactional(readOnly = false)
	public synchronized void deleteByUid(UUID uid) {
		Optional<DemoMongoEntity> pEntitiy = demoRepository.findById(uid.toString());
		if (pEntitiy == null || pEntitiy.isEmpty()) {
			throw new RuntimeException("Missing uuid: " + uid);
		}
		demoRepository.delete(pEntitiy.get());
	}

	@Transactional(readOnly = false)
	public synchronized void deleteAll() {
		demoRepository.deleteAll();
	}

}
