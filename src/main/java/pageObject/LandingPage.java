package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LandingPage {
	
	public WebDriver driver;
	
	By uname= By.id("txtUsername");
	By pwd= By.id("txtPassword");
	By loginbutton= By.id("btnLogin");
	By rolebutton= By.id("loginAsButtonGroup");
	By rolename= By.id("account-name");
	By pim=By.xpath("//*[@id=\'menu_pim_viewPimModule\']/a/span[2]");
	By employeeList=By.cssSelector("#menu_pim_viewEmployeeList");
	By logoutArrow=By.xpath("//*[@id=\"account-job\"]/i");
	By logoutAction=By.id("logoutLink");
	
	public LandingPage(WebDriver driver) {
		
		this.driver=driver;
		 
	}

	public WebElement getUsername() 
	{
		return driver.findElement(uname);
	}
	
	public WebElement getPwd() 
	{
		return driver.findElement(pwd);
	}
	
	public WebElement getLoginButton() 
	{
		return driver.findElement(loginbutton);
	}
	
	public WebElement getRoleButton() 
	{
		return driver.findElement(rolebutton);
	}
	
	public WebElement getRole() 
	{
		return driver.findElement(rolename);
	}
	
	
	public WebElement getPIM() 
	{
		return driver.findElement(pim);
	}
	
	public WebElement getEmployee() 
	{
		return driver.findElement(employeeList);
	}

	public WebElement getLogSelection() 
	{
		return driver.findElement(logoutArrow);
	}

	public WebElement getLogout() 
	{
		return driver.findElement(logoutAction);
	}


}
