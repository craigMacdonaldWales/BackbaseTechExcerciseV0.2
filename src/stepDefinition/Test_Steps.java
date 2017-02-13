package stepDefinition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberTest.dataUtils;

import org.junit.Assert;

public class Test_Steps {

	public static WebDriver driver;
	public static String name;
	public static String introduced;
	public static String discontinued;
	public static String company;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//
	@Given("^I am on the CRUD web portal$")
	public void user_is_on_Home_Page() throws Throwable {
		//WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "geckodriver"); // need to document this

		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	   
	    driver.get("http://computer-database.herokuapp.com/computers"); //goto the CRUD portal
	    
		}
	

	@Given("^I have clicked the add a new computer button$")
	public void i_have_clicked_the_add_a_new_computer_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.cssSelector("#add")).click(); // click the add button
	    //throw new PendingException();
	}

	@Given("^I have entered computer details$")
	public void i_have_entered_computer_details_from_dataset(DataTable computerDetails) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		List<Map<String,String>> data = computerDetails.asMaps(String.class,String.class);
		
		int milliStamp = dataUtils.getTimeInt();
		//Map<String, String> data : computerDetails.asMaps(String.class, String.class);
		name = (data.get(0).get("Name")+milliStamp); // use name and milliseconds.
		introduced = data.get(0).get("Introduced");
		discontinued = data.get(0).get("Discontinued");
		company = data.get(0).get("Company");
		
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.id("add")).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys(name);
	    driver.findElement(By.id("introduced")).clear();
	    driver.findElement(By.id("introduced")).sendKeys(introduced);
	    driver.findElement(By.id("discontinued")).clear();
	    driver.findElement(By.id("discontinued")).sendKeys(discontinued);
	    new Select(driver.findElement(By.id("company"))).selectByVisibleText(company);
		
	    //throw new PendingException();
	}

	@When("^I click 'create this computer'$")
	public void i_click_create_this_computer() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		driver.findElement(By.cssSelector("input.btn.primary")).click();
		

	}

	@Then("^the computer details I have entered are displayed in the main listing correctly$")
	public void the_computer_details_I_have_entered_are_displayed_in_the_main_listing_correctly() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String expectedMessage = new String();
		expectedMessage = "Done! Computer " + name + "has been created "; // generate expected result
		
		String actualMessage = new String();
		actualMessage = driver.findElement(By.cssSelector("div.alert-message.warning")).getText(); // get actual
		
		Assert.assertEquals(actualMessage, expectedMessage);
		//assertEquals(driver.findElement(By.cssSelector("div.alert-message.warning")).getText(), confMessage);
	
	}

	
}
