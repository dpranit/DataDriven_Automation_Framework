package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter
{
	public static ExtentReports getExtentReport()
	{
		String extentReportFilePath = System.getProperty("user.dir")+"\\reports\\extentreport.html";
		ExtentSparkReporter Sparkreporter = new ExtentSparkReporter(extentReportFilePath);
		
		Sparkreporter.config().setReportName("TutorialsNinja Automation Reults");
		Sparkreporter.config().setDocumentTitle("TutorialsNinja test Automation Reults");
		
		ExtentReports extentreports = new ExtentReports();
		extentreports.attachReporter(Sparkreporter);
		extentreports.setSystemInfo("Selenium Version", "4.8.2");
		extentreports.setSystemInfo("Operating System", "Windows 11");
		extentreports.setSystemInfo("Executed By", "Rohit Jarange");
		
		return extentreports;
	}
}
