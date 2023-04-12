package orbartal.demo.spring.mongodb.tester.testtask.worker;

import java.util.Comparator;

import org.junit.runner.Description;
import org.junit.runner.Request;

import orbartal.demo.spring.mongodb.tester.test.runner.TestBeanRunner;

public class TestTaskWorkerFactory {

	public static TestTaskWorker fromTestClass(Class<?> junitTestClass) {
		Request request = Request.classes(junitTestClass);
		JunitRunner jrunner = new JunitRunner(request.getRunner());
		return new TestTaskWorker(jrunner);
	}

	public static TestTaskWorker fromTestClassWithOrder(Class<?> junitTestClass) {
		Comparator<Description> comparator = (t1, t2) -> t1.getMethodName().compareTo(t2.getMethodName());
		Request request = Request.aClass(junitTestClass).sortWith(comparator);
		JunitRunner jrunner = new JunitRunner(request.getRunner());
		return new TestTaskWorker(jrunner);
	}

	public static TestTaskWorker fromTestBeanRunner(TestBeanRunner bean) {
		JunitRunner jrunner = new JunitRunner(bean);
		return new TestTaskWorker(jrunner);
	}

}
