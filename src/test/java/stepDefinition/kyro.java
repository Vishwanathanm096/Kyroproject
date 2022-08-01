package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class kyro {

	WebDriver driver;

	@Given("Login into the Kyro Application")
	public void login_into_the_kyro_application() throws Exception {

		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://kyro.pages.dev/");
		driver.findElement(By.className("LoginButton_login_button__ehTMa")).click();
		driver.findElement(By.id("username")).sendKeys(getdata(1, 0));
		driver.findElement(By.id("password")).sendKeys(getdata(1, 1));
		driver.findElement(By.xpath("//button[text()='Continue']")).click();

	}

	@Given("Navigate to Dashboard and then navigate to Kyro Version {double}")
	public void navigate_to_dashboard_and_then_navigate_to_kyro_version(Double double1) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='Dashboard']")).click();
		driver.findElement(By.xpath("//div[normalize-space()='Kyro Version 4.0']")).click();
		driver.findElement(By.cssSelector("button[texttransform ='none']")).click();

	}

	@When("Create a new task")
	public void create_a_new_task() throws Exception {

		driver.findElement(By.name("summary")).sendKeys(getdata(1, 2));
		driver.findElement(By.name("task_type")).sendKeys(getdata(1, 3));
		driver.findElement(By.name("description")).sendKeys(getdata(1, 4));
		driver.findElement(By.id(":r10:")).click();
		List<WebElement> lst = driver.findElements(By.xpath("//*[@id=\"menu-priority\"]/div[3]/ul/li"));
		lst.get(0).click();
		driver.findElement(By.name("location")).sendKeys(getdata(1, 5));
		driver.findElement(By.name("start_date")).sendKeys(getdata(1, 6));
		driver.findElement(By.name("due_date")).sendKeys(getdata(1, 7));
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		Thread.sleep(2000);

	}

	@Then("validate the new task created in the list view")
	public void validate_the_new_task_created_in_the_list_view() throws Exception {

		String expected_url = "https://kyro.pages.dev/tasks?project=62e16c33bc9268150e993307";
		String Current_url = driver.getCurrentUrl();
		Assert.assertTrue(expected_url.equals(Current_url));
		File f1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File f2 = new File("./screenshot/taskviewscreen.jpg");
		FileHandler.copy(f1, f2);
		driver.quit();

	}

	public static String getdata(int i, int j) throws Exception {

		FileInputStream fis = new FileInputStream(".\\excel\\testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String s1 = wb.getSheet("users").getRow(i).getCell(j).getStringCellValue();
		return (s1);

	}

}
