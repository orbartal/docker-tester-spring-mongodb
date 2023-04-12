package orbartal.demo.spring.mongodb.tester.testtask.model;

import orbartal.demo.spring.mongodb.tester.task.model.TaskReport;
import orbartal.demo.spring.mongodb.tester.test.report.MultiTestsReport;

public class TestTaskReport implements TaskReport {

	private final MultiTestsReport report;

	public TestTaskReport(MultiTestsReport report) {
		this.report = report;
	}

	public MultiTestsReport getReport() {
		return report;
	}

}
