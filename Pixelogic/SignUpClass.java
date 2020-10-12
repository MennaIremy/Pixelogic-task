package PixelogicTest.Pixelogic;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SignUpClass extends baseClass {
		
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static final String SMALL_STRING = "abcdefghijklmnopqrstuvwxyz";
	
	private static final String Numeric_STRING = "0123456789";
		
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
	
	WebElement firstName, lastName, MobileNumber, Email, password, confirmPassword, signUpButton;
	
	String firstNameString, lastNameString, mobileNumberString, emailString, passwordString, URI;

	public Response response;
	
	public RequestSpecification request;
		
	Map<String, Response> testCasesResponses = new HashMap<String, Response>();

	@BeforeMethod
	public void findElements ()
	{			
					
		driver.get("https://www.phptravels.net/register");
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		firstName = driver.findElement(By.name("firstname"));
		
		lastName = driver.findElement(By.name("lastname"));
		
		MobileNumber = driver.findElement(By.name("phone"));

		Email = driver.findElement(By.name("email"));
		
		password = driver.findElement(By.name("password"));
		
		confirmPassword = driver.findElement(By.name("confirmpassword"));

		signUpButton = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[8]/button"));
	}
	
	@Test(priority = 1)
	public void signUpSuccessfully ()
	{
		
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		signUpButton.click();
		
	    WebDriverWait wait = new WebDriverWait(driver, 40);
	    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div/div/div[1]/div/div[1]/img")));
	
		URI = driver.getCurrentUrl();
				
		assertEquals(URI, "https://www.phptravels.net/account/");
		
		RestAssured.baseURI = URI;
		
		request = RestAssured.given();
		
		response = request.get();		
		
		int responseCode = response.getStatusCode();
		
		assertEquals(responseCode, 200);
		
		WebElement profile = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div/div[1]/div[1]/aside/nav/ul/li[2]/a"));
		
		profile.click();
				
		WebElement profileEmail = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div/div/form/div/div/div[2]/div[1]/div/input"));

		assertEquals(profileEmail.getAttribute("value"), emailString );
		
		testCasesResponses.put("signUpSuccessfully", response);
		
		WebElement logoutList = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div"));
		
		logoutList.click();
		
		WebElement logout = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div/div/div/a[2]"));
		
		logout.click();
							
	}
	
	@Test(priority = 2)
	public void loginSuccessfully ()
	{
		WebElement loginList = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div"));
			
		loginList.click();
			
		WebElement loginOption = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div/div/div/a[1]"));
			
		loginOption.click();
			
		WebDriverWait wait = new WebDriverWait(driver, 40);
			    
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		        
		WebElement loginEmail = driver.findElement(By.name("username"));
			
		WebElement loginPassword = driver.findElement(By.name("password"));
			
		WebElement loginBtn = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div[1]/div[2]/form/button"));
			
		loginEmail.sendKeys(emailString);
			
		loginPassword.sendKeys(passwordString);
			
		loginBtn.click();
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div/div/div[1]/div/div[1]/img")));
			 
		URI = driver.getCurrentUrl();
						
		assertEquals(URI, "https://www.phptravels.net/account/");
				
		RestAssured.baseURI = URI;
				
		request = RestAssured.given();
				
		response = request.get();		
				
		int responseCode = response.getStatusCode();
				
		assertEquals(responseCode, 200);
				
		WebElement profile = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div/div[1]/div[1]/aside/nav/ul/li[2]/a"));
				
		profile.click();
						
		WebElement profileEmail = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div/div/form/div/div/div[2]/div[1]/div/input"));
	
		assertEquals(profileEmail.getAttribute("value"), emailString );
				
		testCasesResponses.put("loginSuccessfully",response);
		
		WebElement logoutList = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div"));
		
		logoutList.click();
		
		WebElement logout = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div/div/div/a[2]"));
		
		logout.click();
	}

	@Test(priority = 3)
	public void registeringWithUsedEmailAssertion ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
				
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		signUpButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
	    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div")));
        
        WebElement alert = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div"));
		
        assertEquals("Email Already Exists.", alert.getText() );
        
        URI = driver.getCurrentUrl();
		
		assertEquals(URI, "https://www.phptravels.net/register");
		
		RestAssured.baseURI = URI;
		
		request = RestAssured.given();
		
		response = request.get();		
		
		int responseCode = response.getStatusCode();
		
		assertEquals(responseCode, 200);
		
		testCasesResponses.put("registeringWithUsedEmailAssertion", response);	
	}
	
	@Test(priority = 4)
	public void registerWithInvalidEmailAddress ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
				
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		emailString = randomAlphaNumeric(4,SMALL_STRING)+".com";
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		signUpButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
	    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div")));
        
        WebElement alert = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div"));
		
        assertEquals("The Email field must contain a valid email address.", alert.getText() );
        
        URI = driver.getCurrentUrl();
		
		assertEquals(URI, "https://www.phptravels.net/register");
		
		RestAssured.baseURI = URI;
		
		request = RestAssured.given();
		
		response = request.get();		
		
		int responseCode = response.getStatusCode();
		
		assertEquals(responseCode, 200);	
		
		testCasesResponses.put("registerWithInvalidEmailAddress", response);	

	}
	
	@Test(priority = 5)
	public void registerWithInvalidEmailAddressWithoutcom ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
				
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		emailString = randomAlphaNumeric(4,SMALL_STRING)+"@";
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		signUpButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
	    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div")));
        
        WebElement alert = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div"));
		
        assertEquals("The Email field must contain a valid email address.", alert.getText() );
        
        URI = driver.getCurrentUrl();
		
		assertEquals(URI, "https://www.phptravels.net/register");
		
		RestAssured.baseURI = URI;
		
		request = RestAssured.given();
		
		response = request.get();		
		
		int responseCode = response.getStatusCode();
		
		assertEquals(responseCode, 200);	
		
		testCasesResponses.put("registerWithInvalidEmailAddressWithoutcom", response);	

	}
	
	@Test(priority = 6)
	public void smallLetterInFirstNameAssertion ()
	{
		firstNameString = randomAlphaNumeric(5,SMALL_STRING);
		
		lastNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		assertTrue(Character.isUpperCase(firstNameString.charAt(0)));
		
	}
	
	@Test(priority = 7)
	public void smallLetterInLastNameAssertion ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = randomAlphaNumeric(5,SMALL_STRING);
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		assertTrue(Character.isUpperCase(lastNameString.charAt(0)));
		
	}
	
	@Test(priority = 8)
	public void firstNameEqualLastNameAssertion ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = firstNameString;
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		assertNotEquals(lastNameString, firstNameString);
		
	}

	@Test(priority = 9)
	public void InvalidMobileNumberAssertion ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = firstNameString;
		
		mobileNumberString = randomAlphaNumeric(4,SMALL_STRING)+"#$%^#";
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		assertTrue(mobileNumberString.matches("[0-9]+") && mobileNumberString.length() > 2);

	}
	
	@Test(priority = 10)
	public void invalidPasswordLessthanSixCharacters ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = firstNameString;
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		signUpButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
	    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div")));
        
        WebElement alert = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[2]/div"));
		
        assertEquals("The Password field must be at least 6 characters in length.", alert.getText() );
        
        URI = driver.getCurrentUrl();
		
		assertEquals(URI, "https://www.phptravels.net/register");
		
		RestAssured.baseURI = URI;
		
		request = RestAssured.given();
		
		response = request.get();		
		
		int responseCode = response.getStatusCode();
		
		assertEquals(responseCode, 200);	
		
		testCasesResponses.put("invalidPasswordLessthanSixCharacters", response);	

	}
	
	@Test(priority = 10)
	public void PasswordValidation ()
	{
		firstNameString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(4,SMALL_STRING);
		
		lastNameString = firstNameString;
		
		mobileNumberString = randomAlphaNumeric(11,Numeric_STRING);
		
		emailString = randomAlphaNumeric(5,SMALL_STRING)+"@"+randomAlphaNumeric(5, SMALL_STRING)+".com";
		
		passwordString = randomAlphaNumeric(1,ALPHA_STRING)+randomAlphaNumeric(1,SMALL_STRING)+ randomAlphaNumeric(6,ALPHA_NUMERIC_STRING);
		
		firstName.sendKeys(firstNameString);
		
		lastName.sendKeys(lastNameString);
		
		MobileNumber.sendKeys(mobileNumberString);
		
		Email.sendKeys(emailString);
		
		password.sendKeys(passwordString);
		
		confirmPassword.sendKeys(passwordString);
		
		Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
		
		assertTrue(textPattern.matcher(passwordString).matches() && passwordString.length()<9);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}


}
