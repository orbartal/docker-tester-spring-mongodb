package orbartal.demo.spring.mongodb.tester.testtask.worker;

import orbartal.demo.spring.mongodb.tester.task.model.TaskReport;

public interface TaskWorker <R extends TaskReport> {

	public R work();

}
