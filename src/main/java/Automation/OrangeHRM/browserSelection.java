package Automation.OrangeHRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class browserSelection {
	
	public WebDriver driver;
	public Properties prop;
public WebDriver IntilaizeDriver() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\naveen.baskaran\\Desktop\\Selenium\\Automation\\src\\main\\java\\Automation\\data.properties");
		//FileInputStream fis = new FileInputStream("src/main/java/Automation/OrangeHRM/data.properties");
		prop.load(fis);
		String browserName= prop.getProperty("browser");
		
		if (browserName.contentEquals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			 driver= new ChromeDriver();
		}
		else if(browserName.contentEquals("firefox"))
		{
		}
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;

}
}
