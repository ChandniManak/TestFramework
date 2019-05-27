import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestServer {
	
	static ExtentTest test;
	static ExtentReports report;

	WebDriver driver;

	 @BeforeTest
	 public void init() {
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
	    System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	 }

	 @AfterTest
	 public void end() {
		 report.endTest(test);
		 report.flush();
    	 driver.quit();
	 }
	 
	 @Test
	 public void testLoadBalance() {
		 driver.get("http://52.172.142.170");
		 if(driver.getTitle().equals("Apache Tomcat/8.5.41"))
				 test.log(LogStatus.PASS, "Load balancing is successful");

		 else
			 test.log(LogStatus.FAIL, "Load Balancing failed");
	}

	 @Test
	 public void testTitle() {
		 driver.get("http://52.172.142.170/SpringMVCHibernate");
		 if(driver.getTitle().equals("Person Page"))
				 test.log(LogStatus.PASS, "Project page launched");

		 else
			 test.log(LogStatus.FAIL, "Unable to launch project page");
	}
}
