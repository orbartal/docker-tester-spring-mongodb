package orbartal.demo.spring.mongodb.tester.task.app;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.demo.spring.mongodb.tester.task.api.model.TaskStatusResponseDto;
import orbartal.demo.spring.mongodb.tester.task.app.mapper.TaskResponseFactory;
import orbartal.demo.spring.mongodb.tester.task.data.TaskDataReader;
import orbartal.demo.spring.mongodb.tester.task.model.TaskReport;
import orbartal.demo.spring.mongodb.tester.task.model.TaskStatusEnum;

@Component
public class TaskAppReader {

	@Autowired
	private TaskDataReader taskReader;

	@Autowired
	private TaskResponseFactory responseFactory;

	public TaskStatusResponseDto readTaskStatusById(String id) {
		UUID uid = UUID.fromString(id);
		Optional<TaskStatusEnum> status = taskReader.getStatus(uid);
		if (status.isPresent()) {
			return responseFactory.status(id, status);
		}
		throw new RuntimeException("Not found task for uid");
	}

	public TaskReport readTaskReportById(String id) {
		UUID uid = UUID.fromString(id);
		Optional<TaskReport> r = taskReader.getReport(uid);
		if (r.isPresent()) {
			return r.get();
		}
		throw new RuntimeException("Not found task report for uid");
	}

}
