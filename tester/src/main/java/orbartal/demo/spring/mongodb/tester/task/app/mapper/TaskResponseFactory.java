package orbartal.demo.spring.mongodb.tester.task.app.mapper;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import orbartal.demo.spring.mongodb.tester.task.api.model.TaskCreateResponseDto;
import orbartal.demo.spring.mongodb.tester.task.api.model.TaskStatusResponseDto;
import orbartal.demo.spring.mongodb.tester.task.model.RunnableTask;
import orbartal.demo.spring.mongodb.tester.task.model.TaskStatusEnum;

@Component
public class TaskResponseFactory {

	public TaskCreateResponseDto create(UUID uuid, RunnableTask task) {
		TaskCreateResponseDto dto = new TaskCreateResponseDto();
		dto.setName(task.getName());
		dto.setUid(uuid.toString());
		return dto;
	}

	public TaskStatusResponseDto status(String id, Optional<TaskStatusEnum> status) {
		TaskStatusResponseDto dto = new TaskStatusResponseDto();
		dto.setUid(id);
		dto.setStatus(status.get().name());
		return dto;
	}

}
