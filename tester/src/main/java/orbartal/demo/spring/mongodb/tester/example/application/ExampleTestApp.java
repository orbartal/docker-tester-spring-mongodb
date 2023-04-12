package orbartal.demo.spring.mongodb.tester.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.demo.spring.mongodb.tester.example.test.CallOrderTest;
import orbartal.demo.spring.mongodb.tester.example.test.Test4Results;
import orbartal.demo.spring.mongodb.tester.task.api.model.TaskCreateResponseDto;
import orbartal.demo.spring.mongodb.tester.task.app.TaskAppWriter;
import orbartal.demo.spring.mongodb.tester.task.model.RunnableTask;
import orbartal.demo.spring.mongodb.tester.testtask.runnable.TestRunnableTask;
import orbartal.demo.spring.mongodb.tester.testtask.worker.TestTaskWorker;
import orbartal.demo.spring.mongodb.tester.testtask.worker.TestTaskWorkerFactory;

@Component
public class ExampleTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto test4Results() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClass(Test4Results.class);
		RunnableTask task = new TestRunnableTask("test4Results", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCallOrder() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClass(CallOrderTest.class);
		RunnableTask task = new TestRunnableTask("testCallOrder", worker);
		return taskWriter.runTask(task);
	}

}
