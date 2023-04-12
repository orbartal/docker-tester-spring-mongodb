package orbartal.demo.spring.mongodb.tester.task.app;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.demo.spring.mongodb.tester.task.api.model.TaskCreateResponseDto;
import orbartal.demo.spring.mongodb.tester.task.app.mapper.TaskResponseFactory;
import orbartal.demo.spring.mongodb.tester.task.data.TaskDataWriter;
import orbartal.demo.spring.mongodb.tester.task.model.RunnableTask;


@Component
public class TaskAppWriter {

	@Autowired
	private TaskResponseFactory responseFactory;

	@Autowired
	private TaskDataWriter taskExecuter;

	public TaskCreateResponseDto runTask(RunnableTask task) {
		UUID uuid = taskExecuter.addNewTask(task);
		return responseFactory.create(uuid, task);
	}

}
