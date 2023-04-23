package base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base 
{
	public Properties prop;
	
	public WebDriver openBrowserAndApplicationURL(String BrowserName)
	{
		prop = new Properties();
		File propfile = new File("src\\test\\resources\\data.properties");
		try {
			FileInputStream fis = new FileInputStream(propfile);
			prop.load(fis );
			}
		catch (Throwable e) 
			{
			e.printStackTrace();
			}
		
		WebDriver driver = null;
		
		if(BrowserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		}
		else if (BrowserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
