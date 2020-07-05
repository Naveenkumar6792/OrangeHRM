package Automation.OrangeHRM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilis.ExcelUtils;
import pageObject.LandingPage;

public class HomePage extends browserSelection {
	
	public static final Integer ITERATION_COUNT=4;

@Test(dataProvider="GetExcelData") // Getting value from Excel
	
	public void basePageNavigation(Integer intCount, HashMap<String,String> objData) throws IOException, InterruptedException
	{
		
		driver= IntilaizeDriver();
		driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
		driver.manage().window().maximize();
		//Retrieving driver object from Landing Page
		LandingPage home= new LandingPage(driver); 
		
		// Clearing auto fetched values
		home.getUsername().clear();		
		home.getPwd().clear();
		
		//Fetching Expected result
		
		String expectedval=objData.get("Expected Value"); 
		System.out.println(expectedval);
		
		//Selection of different role button
		home.getRoleButton().click();		
		
		driver.findElement(By.linkText(objData.get("Role"))).click(); 		//Selecting Role from test data
		
		if(driver.findElement(By.id("account-name")).isDisplayed())
		{
			//Fetching Account name for validation
			String account=driver.findElement(By.id("account-name")).getText(); 	
			System.out.println(account);
			
			//Assert Validation
			Assert.assertEquals(expectedval, account);	
			
			
			// Logging out
			home.getLogSelection().click();	
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutLink")));
			home.getLogout().click();
		}
		else
		{
			System.out.println("Invalid Login Credentials");
		}
		
		
		// Closing the browser
		driver.close(); 
	}

@Test(testName="Employee List")

public void employeeList() throws IOException, InterruptedException
{
	
	driver= IntilaizeDriver();
	driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
	driver.manage().window().maximize();
	LandingPage home= new LandingPage(driver);
	
	//Clearing auto populated values
	home.getUsername().clear();
	home.getUsername().sendKeys("_ohrmSysAdmin_");
	
	//Clearing auto populated values
	home.getPwd().clear();
	home.getPwd().sendKeys("sysadmin");
	
	//Clicking on Login Button
	home.getLoginButton().click();
	
	//Navigating to PIM
	home.getPIM().click();
	
	//Clicking on EmployeeList
	home.getEmployee().click();
	
	//Fetching Employee Names displayed
	WebElement table =driver.findElement(By.id("employeeListTable"));
	System.out.println(table.findElements(By.tagName("thead")).get(0).findElements(By.tagName("tr")).get(0).findElements(By.tagName("th")).size());
	Thread.sleep(5000);
	int col=table.findElements(By.tagName("tbody")).get(0).findElements(By.tagName("tr")).size();
	//System.out.println(col);
	List<WebElement> rowval=table.findElements(By.tagName("tbody")).get(0).findElements(By.tagName("tr")).get(0).findElements(By.tagName("td"));
	
	for(int i = 0;i<col;i++)
	{
		System.out.println(rowval.get(i).getText()); // Printing the values in output 
	}
		
	// Closing the browser
	driver.close(); 
}


@DataProvider(parallel=false)
	public Object[][] GetExcelData(){
	
		ExcelUtils objUtil = new ExcelUtils();
		XSSFWorkbook objWork = null;
		try {
		objWork = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\naveen.baskaran\\Desktop\\LoginValidation.xlsx")));
		//objWork = new XSSFWorkbook(new FileInputStream(new File("LoginValidation.xlsx")));
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		objUtil.setObjWorkbook(objWork);
		
		return objUtil.GetExcelData("Sheet1",ITERATION_COUNT);
		
	}

}
