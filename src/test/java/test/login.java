package test;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.base;
import pageobject.AccountPage;
import pageobject.HomePage;
import pageobject.LoginPage;
import util.DataUtil;
import util.MyXLSReader;

public class login extends base
{
	WebDriver driver;
	MyXLSReader excelReader;
	
	@AfterMethod
	public void teardown() 
	{
		driver.quit();
	}
	
	
	@Test(dataProvider = "datasupplier")
	public void testlogin(HashMap<String, String> hMap)
	{
		if(!DataUtil.isRunnable(excelReader,"LoginTest", "Testcases") || hMap.get("Runmode").equals("N"))
		{
			throw new SkipException("Run mode is set to N, hence not executed");
		}
		
		driver = openBrowserAndApplicationURL(hMap.get("Browser"));
		
		HomePage homepage = new HomePage(driver);
		homepage.ClickOnMyAccountDropMenu();
		
		LoginPage loginPage = homepage.selectLoginOption();
		loginPage.enterEmailAddress(hMap.get("Username"));
		loginPage.enterPassword(hMap.get("Password"));
		
		AccountPage accountPage = loginPage.clickOnLoginButton();
		
		
		String expectedResult = hMap.get("ExpectedResult");
		
		boolean expectedConvertedResult = false;
		
		if(expectedResult.equalsIgnoreCase("Success"))
		{
			expectedConvertedResult = true;
		}
		else if (expectedResult.equalsIgnoreCase("Failure")) 
		{
			expectedConvertedResult = false;
		}
		
		boolean actualResult = false;
		
		actualResult = accountPage.verifyTheDisplayOfEditYourAccountInformationOption();
		
		Assert.assertEquals(actualResult, expectedConvertedResult);
				
	}
	
	@DataProvider
	public Object[][] datasupplier() 
	{
		Object[][] data = null;
		
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TutorialsNinja.xlsx");
		
		data = DataUtil.getTestData(excelReader,"LoginTest","Data");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
