package util;

import testdata.CommonProperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager {


	    private static ExtentReports extent;
	    private static ExtentXReporter extentxReporter;

	    public  static synchronized ExtentReports getInstance(String fileName) {
	        if (extent == null)
	            createInstance(fileName);

	        return extent;
	    }

	    public  static synchronized ExtentReports createInstance(String fileName) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);


	        extentxReporter = new ExtentXReporter(CommonProperties.MONGODBHOST, CommonProperties.MONGODBPORT);
	        extentxReporter.config().setProjectName("MoonTest");
	        extentxReporter.config().setReportName(fileName);
	        extentxReporter.config().setServerUrl("http://" + CommonProperties.EXTENTXIP);


	        extent = new ExtentReports();

//	        extent.attachReporter(extentxReporter,htmlReporter);

	        return extent;
	    }
	
}
