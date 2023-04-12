package orbartal.demo.spring.mongodb.tester.testtask.worker;

import org.junit.runner.JUnitCore;
import org.junit.runner.Runner;

import orbartal.demo.spring.mongodb.tester.test.runner.TestListener;

public class JunitRunner {

	private final Runner runner;

	public JunitRunner(Runner runner) {
		this.runner = runner;
	}

	public void run(TestListener listener) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(listener);
		junit.run(runner);
	}

}
