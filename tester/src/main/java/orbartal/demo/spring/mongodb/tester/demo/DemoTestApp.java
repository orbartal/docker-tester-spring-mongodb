package orbartal.demo.spring.mongodb.tester.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.demo.spring.mongodb.tester.demo.test.CrudMultiErrorTest;
import orbartal.demo.spring.mongodb.tester.demo.test.CrudMultiValidTest;
import orbartal.demo.spring.mongodb.tester.demo.test.CrudOneValidTest;
import orbartal.demo.spring.mongodb.tester.task.api.model.TaskCreateResponseDto;
import orbartal.demo.spring.mongodb.tester.task.app.TaskAppWriter;
import orbartal.demo.spring.mongodb.tester.task.model.RunnableTask;
import orbartal.demo.spring.mongodb.tester.testtask.runnable.TestRunnableTask;
import orbartal.demo.spring.mongodb.tester.testtask.worker.TestTaskWorker;
import orbartal.demo.spring.mongodb.tester.testtask.worker.TestTaskWorkerFactory;

@Component
public class DemoTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto testCrudOneValid() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudOneValidTest.class);
		RunnableTask task = new TestRunnableTask("testCrudOneValid", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCrudManyValid() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudMultiValidTest.class);
		RunnableTask task = new TestRunnableTask("testCrudManyValid", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCrudManyError() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudMultiErrorTest.class);
		RunnableTask task = new TestRunnableTask("testCrudManyError", worker);
		return taskWriter.runTask(task);
	}

}
