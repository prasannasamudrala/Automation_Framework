package base.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Base {
	public  static WebDriver driver;
	public static Actions acn;
	@BeforeSuite
	public void tearUp() throws IOException{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("E:\\Mani Evng\\Automation_Framework\\config.properties");
		p.load(fis);
		String browsertype = p.getProperty("browser");
		if(browsertype.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browsertype.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "E:\\Mani Evng\\Automation_Framework\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browsertype.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", "E:\\Mani Evng\\Automation_Framework\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
		
	}
	@AfterSuite
	public void tearDown(){
		
		driver.close();
		driver.quit();
		
	}

}
