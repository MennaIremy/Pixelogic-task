package PixelogicTest.Pixelogic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class baseClass {
	
	public static WebDriver driver;

	//randomAlphaNumeric method is used to generate random strings according to the input given to it
	// it can produce random string of numbers or letters whether capital or small
	
		protected static String randomAlphaNumeric(int count, String tmp)
		{	
			StringBuilder builder = new StringBuilder();
			
			while (count-- != 0)
			{
				int character = (int)(Math.random()*tmp.length());
				
				builder.append(tmp.charAt(character));
				
			}
			return builder.toString();
		}
		
		
		@BeforeClass
		public void setUp()
		{
						
			FirefoxProfile profile = new FirefoxProfile();
			
			// disable the cache disk, cash memory along with offline cache and use-cache
			
			profile.setPreference("browser.cache.disk.enable", false);
			
			profile.setPreference("browser.cache.memory.enable", false);
			
			profile.setPreference("browser.cache.offline.enable", false);
			
			profile.setPreference("network.http.use-cache", false);
			
			//options receives the profile options we edited for firefox 
			
			FirefoxOptions options = new FirefoxOptions().setProfile(profile);
					
			driver = new FirefoxDriver(options);
			
			
		}

	
	
}