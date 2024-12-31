package com.ltim.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ltim.testbase.BaseClass1;

public class ExtentReportManager {
	public ExtentReports extentReport;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest test;
	public String repName;

	// startup information on Report
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName);
		//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\" + repName);
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Regression Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Application", "SauceDemo");
		extentReport.setSystemInfo("Sauce", "Login");
		extentReport.setSystemInfo("Sub Module", "Login");
		extentReport.setSystemInfo("User Name", "user.name");
		extentReport.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extentReport.setSystemInfo("Operating System", os);
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extentReport.setSystemInfo("Browser", browser);
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extentReport.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	// display success result in the Report
	public void onTestSuccess(ITestResult result) {
		test = extentReport.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "got successfully executed ");
	}

	// display failure result with screenshot in the Report
	public void onTestFailure(ITestResult result) {
		extentReport.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass1().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// display the skipped test case information in Report
	public void onTestSkipped(ITestResult result) {
		test = extentReport.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	//write content to Report
	public void onFinish(ITestContext testContext) {
		extentReport.flush();
		
		String pathOfExtenntReport = System.getProperty("user.dir") + "\\Reports" + repName;
		File extentReportFile = new File(pathOfExtenntReport);
		//to open the Report automatically in default browser
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}// end class

