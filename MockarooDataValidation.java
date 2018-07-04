package projects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {

	WebDriver driver;
	List<String> data;
	List<String> cities;
	List<String> countries;
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		System.out.println("before");
		 driver.get("https://mockaroo.com/");
	}


	 @Test(priority=1)//3
	public void atitleVerification3() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	 @Test(priority=2)//4
	public void bmockarooIsDisplayed4() {
		WebElement realisticDataGeneratorDisplayed = driver.findElement(By.xpath("//div[@class='tagline']"));
		WebElement MockarooDisplayed = driver.findElement(By.xpath("//div[@class='brand']"));// +" "+
																								// driver.findElement(By.xpath("//div[@class='tagline']")).getText();
		System.out.println(MockarooDisplayed.getText());
		System.out.println(realisticDataGeneratorDisplayed.getText());
		Assert.assertTrue(MockarooDisplayed.isDisplayed());
		Assert.assertTrue(realisticDataGeneratorDisplayed.isDisplayed());
	}

	 @Test(priority=3) // 5
	public void cremoveExistingFieds5() {
		//// a[@class='close remove-field remove_nested_fields']
		List<WebElement> removeAlist = driver.findElements(By.xpath("//div[@class='fields'] "));
		for (WebElement elementList : removeAlist) {
			elementList.click();
		}
	}

	@Test (priority=4)// 6
	public void dFieldNameTypeOptionsAreDisplayed6() {
		List<WebElement> labelDisplayed = driver.findElements(By.xpath("//div[@class='table-header']/div"));
		for (WebElement elementList : labelDisplayed) {
			Assert.assertTrue(elementList.isDisplayed());
		}
	}
	@Test(priority=5)//7
	public void eaddAnotherFieldIsEnabled7() {
		WebElement fieldIsEnabled = driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']"));//.isEnabled();
	Assert.assertTrue(fieldIsEnabled.isEnabled());
	}
	@Test(priority=6)//8
	public void rowIs10008() {
		String actualDefault1000= driver.findElement(By.xpath("//input[@id='num_rows']")).getAttribute("value");
		String expected ="1000";
		Assert.assertEquals(actualDefault1000,expected);
		
	}
	@Test(priority=7) //9
	public void formatSelectionCSV9() {
		Select selected =new Select(driver.findElement(By.xpath("//select[@id='schema_file_format']")));
		String selectedCSV = selected.getFirstSelectedOption().getText();
		assertEquals(selectedCSV, "CSV");
	}
	@Test(priority=8) //10  
	public void glineEndingIsUnixLF10() {

		Select selectedUnix =new Select(driver.findElement(By.xpath("//select[@id='schema_line_ending']")));
		String selectedUnixLF = selectedUnix.getFirstSelectedOption().getText();
		assertEquals(selectedUnixLF, "Unix (LF)");
		
	}
	@Test(priority=9)//11 Assert that header checkbox is checked and BOM is unchecked
	public void headerBom11() {	
		WebElement checked = driver.findElement(By.cssSelector("input#schema_include_header"));
		assertTrue(checked.isSelected());	
		WebElement unChecked = driver.findElement(By.cssSelector("input#schema_bom"));	
		assertTrue(!unChecked.isSelected());
			
		
	}
	@Test (priority=10) //12//dependant
	public void zenterCity12() {
//		 driver.findElement(By.xpath("//a[.='Add another field']")).click();
//		 driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[2]/input")).sendKeys("City"); ////div[@id=‘fields’]/div[7]/div[2]/input
//		 
		driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']"))
		.sendKeys(Keys.ENTER + "city");
	}
	@Test(priority=11)//13
	public void dialogBox13() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='fields']/div[7]/div[3]/input[3]")).click();
		Thread.sleep(1000);
		String chooseAtype = driver.findElement(By.xpath("//div[@id='type_dialog']/div/div/div[1]/h3")).getText();
		
		System.out.println(chooseAtype);

		assertEquals(chooseAtype,"Choose a Type");
	}
	@Test(priority=12)//14
	public void city() {
		driver.findElement(By.xpath("//input[@tabindex='1']")).sendKeys("city");
		driver.findElement(By.xpath("//div[@tabindex='1']")).click();
	}
	@Test(priority=13)//15
	public void country() throws InterruptedException {
		driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']"))
		.sendKeys(Keys.ENTER + "country");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("type_search_field")).clear();
	driver.findElement(By.id("type_search_field")).sendKeys("country");
	driver.findElement(By.xpath("//div[@class = 'type-name' and .='Country']")).click();
	Thread.sleep(1000);
	}
	@Test(priority=14)//16
	public void download() {
//		driver.findElement(By.id("download")).click();
	}
	@Test(priority=15)//17
	public void buffer() throws Exception  {
		Thread.sleep(2000);
		BufferedReader bf = new BufferedReader(new FileReader("/Users/aa/Downloads/MOCK_DATA.csv"));
		data = new ArrayList<>();
		String temp = bf.readLine();
		while (temp != null) {
		    data.add(temp);
		    temp = bf.readLine();
		}
	}
	@Test(priority=16)//18
	public void cityCountry() {
		assertEquals(data.get(0), "city,country");
	}
	@Test(priority=17)//19
	public void dataSize1000() {
		data.remove(0);
		assertEquals(data.size(), 1000);
	}
	@Test(priority=18)//20
	public void sortCities() {
		cities = new ArrayList<>();
		for(String str : data) {
			cities.add(str.substring(0, str.indexOf(",")));
		}
		Collections.sort(cities);
		String cityShort = cities.get(0);
		String cityLong = cities.get(0);
		for (int i = 1; i < cities.size(); i++) {
		    if (cityShort.length() > cities.get(i).length()) {
			cityShort = cities.get(i);
		    }
		    if (cities.get(i).length() > cityLong.length()) {
			cityLong = cities.get(i);
			}
		}
		System.out.println("The city with shortest name in the list is: "+cityShort);
		System.out.println("The city with longest name in the list is: "+cityLong);
	}
	@Test(priority=19)//21
	public void sortCountries() {
		countries = new ArrayList<>();
		for(String str: data) {
			 countries.add(str.substring(str.indexOf(",")+1));
		}
		System.out.println(countries.size()+" countries size");
		SortedSet<String> sortedCountry = new TreeSet<>(countries);
		for (String str : sortedCountry) {
			System.out.println(str + " was listed " + Collections.frequency(countries, str)+" times");
		}
	}
	@Test(priority=20)//22
	public void uniqueCities() {
		Set<String> citiesSet = new HashSet<>(cities);
		int uniqueCityCount=0;
		int notUnique =0;
		for(int i=0; i<cities.size();i++) {
			if(i==cities.lastIndexOf(cities.get(i))) {
				uniqueCityCount++;
		} else {
			notUnique++;}
		}
		System.out.println(uniqueCityCount+" + "+ notUnique);
		assertEquals(uniqueCityCount, citiesSet.size());
	}
	@Test(priority=21)//27
	public void uniqueCountries() {
		Set<String> uniqueCountries = new HashSet<>(countries);
	int uniqueCountryCount=0;
		for(int i=0; i<countries.size();i++) {
		if(i==countries.lastIndexOf(countries.get(i))) {
			uniqueCountryCount++;
		}}
		System.out.println(uniqueCountries.size()+"==="+uniqueCountryCount);
		assertEquals(uniqueCountryCount, uniqueCountries.size());
	
	
	}
	@AfterClass
	public void done() {
		driver.quit();
	}
	
}
