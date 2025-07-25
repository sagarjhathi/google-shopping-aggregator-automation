package amazonfilterapplicatione2e;
import static org.testng.Assert.expectThrows;  
import java.time.Duration; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import amazonfilterapplicatione2e.reporting.ExtentTestManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AmazonTests extends BaseTest {

	private  final Logger log = LoggerUtility.getLogger(AmazonTests.class);
	//private final Logger log = LoggerUtility.getLogger(getClass());
	//Logging and reporting with screen shots would be the next big thing to add after assertions
	//@Test(priority=-1)
	
	@DataProvider(name = "Data")
	public Object[][] getBrands() {
	    return new Object[][] {
	        {"Mobile"},
	        {"Tv"},
	        {"Charger"},
	        {"AC"},
	        {"Fridge"}
	    };
	}
	
	@Test(priority=1, retryAnalyzer = RetryFailedTest.class,dataProvider = "Data")
	public void verifyingGetItByTomorrowFilterFunctionality(String input) throws InterruptedException{
		 ExtentTestManager.getTest().info("🧪 Test Input Parameter: <b>" + input + "</b>");
		AmazonLandingPage am=new AmazonLandingPage();
        am.openingLandingPage();
        am.givingInputWithinSearchBar(input);
        am.clickingOnSubmitSearchButton();                   
		
        ProductListingPage productPage=new ProductListingPage();
        GenericUtility genericUtility=new GenericUtility();
        productPage.refreshIfServiceUnavailable();
       
        if (!genericUtility.isElementVisibleOnUI(productPage.getItByTomorrowUnderDeliveryDayFilterBy)) {
		    System.out.println("Filter option 'Get It by Tomorrow' does not exist. Skipping the test.");
		    log.warn("[{}] ⚠ Filter option 'Get It by Tomorrow' does not exist. Skipping the test.", ThreadContext.get("testName"));
		    return;
		}
        
	    genericUtility.printFilterNamesOnly(productPage.getItByTomorrowUnderDeliveryDayFilterBy); 
	
	    List<Object> result = productPage.validateDeliveryFilterOptionsWithResult(productPage.getItByTomorrowUnderDeliveryDayFilterBy);

	    boolean isValid = (boolean) result.get(0);
	    String text = (String) result.get(1);
	    int index = (int) result.get(2);
	    
	    log.info("[{}] Asserting delivery filter: isValid={}, index={}, text={}", 
	            ThreadContext.get("testName"), isValid, index, text);
	    Assert.assertTrue(isValid,"❌ Delivery date mismatch at index " + index + ". Text: " + text);
	   
	    System.out.println(isValid+"  Text from function =>"+text+" index no is "+index);  
		    
		}
	
	
//	//@Test(priority=-2)
	@Test(priority=2, retryAnalyzer = RetryFailedTest.class,dataProvider = "Data")
	public void verifyingGetItIn2DaysFilterFunctionality(String input) throws InterruptedException{
		 ExtentTestManager.getTest().info("🧪 Test Input Parameter: <b>" + input + "</b>");

		 AmazonLandingPage am=new AmazonLandingPage();
         am.openingLandingPage();
         am.givingInputWithinSearchBar(input);
         am.clickingOnSubmitSearchButton();


         GenericUtility genericUtility=new GenericUtility();
		 ProductListingPage productPage=new ProductListingPage();
		 productPage.refreshIfServiceUnavailable();   
		 
 		if (!genericUtility.isElementInViewport(productPage.getItInTwoDaysUnderDeliveryDayFilterBy)) {
 		    System.out.println("Filter option 'Get It in 2 Days' does not exist. Skipping the test.");
 		   log.warn("[{}] ⚠ Filter option 'Get It in 2 Days' does not exist. Skipping the test.", ThreadContext.get("testName"));
 		    return ;
 		}                 
		            		
 		genericUtility.printFilterNamesOnly(productPage.getItInTwoDaysUnderDeliveryDayFilterBy); 
 		
	    List<Object> result = productPage.validateDeliveryFilterOptionsWithResult(productPage.getItInTwoDaysUnderDeliveryDayFilterBy);

	    boolean isValid = (boolean) result.get(0);
	    String text = (String) result.get(1);
	    int index = (int) result.get(2);
	    
	    log.info("[{}] Asserting delivery filter: isValid={}, index={}, text={}", 
	            ThreadContext.get("testName"), isValid, index, text);
	    Assert.assertTrue(isValid,"❌ Delivery date mismatch at index " + index + ". Text: " + text);
	    
	    System.out.println(isValid+"  Text from function =>"+text+" index no is "+index);      		
		}
	


//	//@Test(priority=-3)
	@Test(priority = 3, retryAnalyzer = RetryFailedTest.class,dataProvider = "Data")
	public void verifyingGetItByTodayFilterFunctionality(String input) throws InterruptedException{
		 ExtentTestManager.getTest().info("🧪 Test Input Parameter: <b>" + input + "</b>");

		AmazonLandingPage am=new AmazonLandingPage();
        am.openingLandingPage();
        am.givingInputWithinSearchBar(input);
        am.clickingOnSubmitSearchButton();

    
        GenericUtility genericUtility=new GenericUtility();
 		ProductListingPage productPage=new ProductListingPage();
 		productPage.refreshIfServiceUnavailable();
 		if (!genericUtility.isElementVisibleOnUI(productPage.getItTodayUnderDeliveryDayFilterBy)) {
 		    System.out.println("Filter option 'Get It Today' does not exist. Skipping the test.");
 		   log.warn("[{}] ⚠ Filter option 'Get It Today' does not exist. Skipping the test.", ThreadContext.get("testName"));
 		    return;
 		}                   
		            		
		            		
 		genericUtility.printFilterNamesOnly(productPage.getItTodayUnderDeliveryDayFilterBy); 
		List<Object> result = productPage.validateDeliveryFilterOptionsWithResult(productPage.getItTodayUnderDeliveryDayFilterBy);
		
	    boolean isValid = (boolean) result.get(0);
	    String text = (String) result.get(1);
	    int index = (int) result.get(2);
	    
	    Assert.assertTrue(isValid,"❌ Delivery date mismatch at index " + index + ". Text: " + text);
	    log.info("[{}] Asserting delivery filter: isValid={}, index={}, text={}", 
	            ThreadContext.get("testName"), isValid, index, text);
	    System.out.println(isValid+"  Text from function =>"+text+" index no is "+index);             		  
		            	    
	    
	}
		            		
		            		
	//@Test(priority=-4)
	@Test(priority=4, retryAnalyzer = RetryFailedTest.class)
	public void verifyingTheBrandsFilterFunctionality(String input) throws InterruptedException {
		
		 ExtentTestManager.getTest().info("🧪 Test Input Parameter: <b>" + input + "</b>");

		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar(input);
		am.clickingOnSubmitSearchButton();
		
		Thread.sleep(3000);
		ProductListingPage productPage=new ProductListingPage();
		
		// the iteration will not work here it has to be changed a bit similar to the price filter as well
		GenericUtility genericUtility=new GenericUtility();
		productPage.refreshIfServiceUnavailable();
		
		if (!genericUtility.filterCheckUnderList("brands")) {
		    System.out.println("Filter option 'brands' does not exist in the list. Skipping the test.");
	 		   log.warn("[{}] ⚠ Filter option 'brands' does not exist in the list. Skipping the test.", ThreadContext.get("testName"));
		    return ;
		}
		

		
		
		List<Map<Object, Object>> allResults = productPage.applyFilterAndValidateBrandsFilterWithResult(
		        productPage.listBrandsOptionsBy, "brands"
		    );

		    SoftAssert softAssert = new SoftAssert();

		    for (Map<Object, Object> result : allResults) {
		        boolean isValid = (boolean) result.get("isValid");
		        String brand = (String) result.get("brand");
		        List<String> mismatches = (List<String>) result.get("mismatches");

		        if (!isValid) {
		        	log.error("[{}] ❌ Brand validation failed for '{}'. {} mismatches found.", ThreadContext.get("testName"), brand, mismatches.size());
		            System.out.println("❌ Brand validation failed for brand: " + brand);
		            for (String detail : mismatches) {
		                System.out.println(detail);
		                log.error("[{}] ✖ Mismatch for '{}': {}", ThreadContext.get("testName"), brand, detail);

		                softAssert.fail(detail);
		            }
		        } else {
		            log.info("[{}] ✅ All product titles matched for brand: '{}'", ThreadContext.get("testName"), brand);
		            System.out.println("✔ All product titles matched for brand: " + brand);
		        }
		    }

		    log.info("[{}] ⏹ Asserting all soft assertions now...", ThreadContext.get("testName"));
		    softAssert.assertAll();
		
		
	}
	
	
//	//@Test(priority=-5)
	@Test(priority=5, retryAnalyzer = RetryFailedTest.class)
	public void verifyingStorageCapacityFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		GenericUtility genericUtility=new GenericUtility();
		ProductListingPage productPage=new ProductListingPage();
		
		productPage.refreshIfServiceUnavailable();
		if (!genericUtility.filterCheckUnderList("Storage Capacity")) {
		    System.out.println("Filter option 'Storage Capacity' does not exist in the list. Skipping the test.");
	 		   log.warn("[{}] ⚠ Filter option 'Storage Capacity' does not exist in the list. Skipping the test.", ThreadContext.get("testName"));
		    return ;
		}
			
		

		
		
//		 // ⏬ Call the main function and get result
	    List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listStorageCapacityOptionsBy,"storagecapacity");

	    log.info("[{}] 🔄 Retrieved {} results from applyFilterAndValidateProductsWithResult.",
	            ThreadContext.get("testName"), results.size());
	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        log.info("[{}] 🔍 Checking if filter '{}' is found in product details.", ThreadContext.get("testName"), filter);

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	log.error("[{}] ❌ Filter '{}' not found.\n🔎 Title: {}\n🔎 Key Features: {}\n🔎 About: {}\n🔎 Tech Details: {}\n-------------------------------------------------------------",
	        	          ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);

	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }

	    softAssert.assertAll(); // 🚨 Important to report all failures

	}

	
//	//@Test(priority=-6)
	@Test(priority=6, retryAnalyzer = RetryFailedTest.class)
	public void verifyingPriceSilderFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		GenericUtility genericUtility=new GenericUtility();
		ProductListingPage productPage=new ProductListingPage();
		productPage.refreshIfServiceUnavailable();
		if (!genericUtility.filterCheckUnderList("Price")) {
		    System.out.println("Filter option 'Price' does not exist in the list. Skipping the test.");
	 		   log.warn("[{}] ⚠ Filter option 'Price' does not exist in the list. Skipping the test.", ThreadContext.get("testName"));
		    return ;
		}
		
		List<Integer> minValues = Arrays.asList(60, 90, 130);
		List<Integer> maxValues = Arrays.asList(80, 120, 160);
		

		
		
		    List<Map<String, Object>> results = productPage.applyPriceSliderAndValidateWithResult(minValues, maxValues);

		    SoftAssert softAssert = new SoftAssert();

		    for (Map<String, Object> result : results) {
		        boolean isValid = (boolean) result.get("isValid");
		        int min = (int) result.get("min");
		        int max = (int) result.get("max");
		        List<String> mismatches = (List<String>) result.get("mismatches");

		        if (!isValid) {
		            log.error("[{}] ❌ Validation failed for price range: {} - {}", ThreadContext.get("testName"), min, max);
		            System.out.println("❌ Validation failed for price range: " + min + " - " + max);
		            for (String msg : mismatches) {
		                System.out.println(msg);
		                log.error("[{}] ✖ {}", ThreadContext.get("testName"), msg);
		                softAssert.fail(msg);
		            }
		            
		        } else {
		            log.info("[{}] ✅ Price range validated successfully: {} - {}", ThreadContext.get("testName"), min, max);

		            System.out.println("✔ Price range validated: " + min + " - " + max);
		        }
		        System.out.println("------------------------------------------------------------");		
		        log.info("[{}] ------------------------------------------------------------", ThreadContext.get("testName"));

		        }
		    
		    log.info("[{}] ⏹ Asserting all soft assertions now...", ThreadContext.get("testName"));


		    softAssert.assertAll();
					
	}

	
//	//@Test(priority=-7)
	@Test(priority = 7, retryAnalyzer = RetryFailedTest.class)
	public void verifyingBatteryCapacityFilterFunctionality() throws InterruptedException {

	    AmazonLandingPage am = new AmazonLandingPage();
	    am.openingLandingPage();
	    am.givingInputWithinSearchBar("Mobile");
	    am.clickingOnSubmitSearchButton();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


	    ProductListingPage productPage =new ProductListingPage();
	    productPage.refreshIfServiceUnavailable();
	    GenericUtility genericUtility=new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Battery Capacity")) {
	        log.warn("[{}] ⚠ Filter option 'Battery Capacity' does not exist in the list. Skipping the test.", ThreadContext.get("testName"));

		    System.out.println("Filter option 'Battery Capacity' does not exist in the list. Skipping the test.");
		    return ;
		}

		

	    

	    
	    
		
		List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listBatteryCapacityOptionsBy,"batterycapacity");

	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	log.error("[{}] ❌ Battery capacity filter '{}' not found.\n🔍 Details:\n- Title: {}\n- Key Features: {}\n- About: {}\n- Tech Details: {}\n-------------------------------------------------------------",
	        		    ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);

	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            log.info("[{}] ✅ Filter '{}' matched in product details.", ThreadContext.get("testName"), filter);

	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }

	    log.info("[{}] ⏹ Asserting all soft assertions now...", ThreadContext.get("testName"));

	    softAssert.assertAll(); 
	}

	
//	//@Test(priority=8)
	@Test(priority=8, retryAnalyzer = RetryFailedTest.class)
	public void verifyingDisplaySizeFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
          GenericUtility genericUtility=new GenericUtility();
         
  		  ProductListingPage productPage=new ProductListingPage();
  		productPage.refreshIfServiceUnavailable();
		if (!genericUtility.filterCheckUnderList("Display Size")) {
	        log.warn("[{}] ⚠ Filter option 'Display Size' does not exist in the list. Skipping the test.", ThreadContext.get("testName"));

		    System.out.println("Filter option 'Display Size' does not exist in the list. Skipping the test.");
		    return ;
		}
		
		
		
		List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listDisplaySizeOptionsBy,"displaysize");

	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	 log.error("[{}] ❌ Display Size filter '{}' not found.\n🔍 Details:\n- Title: {}\n- Key Features: {}\n- About: {}\n- Tech Details: {}\n-------------------------------------------------------------",
	                     ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);

	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            log.info("[{}] ✔ Filter '{}' matched in at least one section.", ThreadContext.get("testName"), filter);

	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }

	    log.info("[{}] ⏹ Final assertion for display size filter validation starting...", ThreadContext.get("testName"));

	    softAssert.assertAll(); 
	
	}

	
//	//@Test(priority=9)
	@Test(priority=9, retryAnalyzer = RetryFailedTest.class)
	public void verifyingProcessorSpeedFilterFunctionality() throws InterruptedException {
		
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
	
		ProductListingPage productPage=new ProductListingPage();
        GenericUtility genericUtility =new GenericUtility();
        
        productPage.refreshIfServiceUnavailable();
		if (!genericUtility.filterCheckUnderList("Processor Speed")) {
	        log.warn("[{}] ⚠ Filter option 'Processor Speed' not available. Skipping test.", ThreadContext.get("testName"));

		    return ;
		}
		

		
		
		
		List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listProcessorSpeedOptionsBy,"processorspeed");

	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	log.error("[{}] ❌ Processor Speed filter '{}' not found.\n🔍 Details:\n- Title: {}\n- Key Features: {}\n- About: {}\n- Tech Details: {}\n-------------------------------------------------------------",
	                    ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);
	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            log.info("[{}] ✔ Filter '{}' matched in at least one section.", ThreadContext.get("testName"), filter);

	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }
	    
	    log.info("[{}] ⏹ Final assertion for processor speed filter validation starting...", ThreadContext.get("testName"));


	    softAssert.assertAll(); 
		
	}

	
//	//@Test(priority=10)
	@Test(priority=10, retryAnalyzer = RetryFailedTest.class)
	public void verifyingDisplayTypeFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		ProductListingPage productPage=new ProductListingPage();
		productPage.refreshIfServiceUnavailable();
		GenericUtility genericUtility=new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Display Type")){
		    System.out.println("Filter option 'Display Type' does not exist. Skipping the test.");
	        log.warn("[{}] ⚠ Filter option 'Display Type' does not exist. Skipping the test.", ThreadContext.get("testName"));

		    return ;
		}
		
		
		
		List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listDisplayTypeOptionsBy ,"displaytype");

	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	
	        	log.error("[{}] ❌ Display Type filter '{}' not found.\n🔍 Title: {}\n🔍 Key Features: {}\n🔍 About: {}\n🔍 Tech Details: {}",
	                    ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);
	            log.info("[{}] -------------------------------------------------------------", ThreadContext.get("testName"));
	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            log.info("[{}] ✔ Filter '{}' matched in at least one section.", ThreadContext.get("testName"), filter);

	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }

	    log.info("[{}] ⏹ Final assertion for display type filter validation...", ThreadContext.get("testName"));

	    softAssert.assertAll(); 
		}

	
//	@Test(priority=11)
	//@Test(priority=11, retryAnalyzer = RetryFailedTest.class)
	public void verifyingOperatingSystemVersionFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
	
		GenericUtility genericUtility=new GenericUtility();	

		ProductListingPage productPage=new ProductListingPage();
		productPage.refreshIfServiceUnavailable();
		if (!genericUtility.filterCheckUnderList("Operating System", "Operating System Version")) {
		    System.out.println("Filter option 'Operating System' or 'Operating System Version' does not exist in the list. Skipping the test.");
	        log.warn("[{}] ⚠ Filter option 'Operating System' or 'Operating System Version' not found. Skipping test.", ThreadContext.get("testName"));

		    return;
		}
		
		
		
		List<Map<String, Object>> results = productPage.applyOperatingSystemFilterAndValidateProductsWithResults(productPage.listOperatingSystemVersionOptionsBy, "operatingsystem");

	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	log.error("[{}] ❌ OS Version filter '{}' not found.\n🔍 Title: {}\n🔍 Key Features: {}\n🔍 About: {}\n🔍 Tech Details: {}",
	                    ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);
	            log.info("[{}] -------------------------------------------------------------", ThreadContext.get("testName"));
	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            log.info("[{}] ✔ Filter '{}' matched in at least one section.", ThreadContext.get("testName"), filter);

	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }

	    log.info("[{}] ⏹ Final assertion for OS Version filter validation...", ThreadContext.get("testName"));

	    softAssert.assertAll(); 
		
	}

	
//	@Test(priority=12)
	//@Test(priority=12, retryAnalyzer = RetryFailedTest.class)
	public void verifyingMobilePhonePrimaryCameraResolutionFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		GenericUtility genericUtility= new GenericUtility();
		ProductListingPage productPage=new ProductListingPage();
		productPage.refreshIfServiceUnavailable();

			if (!genericUtility.filterCheckUnderList("Mobile Phone Primary Camera Resolution")) {
			    System.out.println("Filter option 'Mobile Phone Primary Camera Resolution' does not exist in the list. Skipping the test.");
			    log.warn("[{}] ⚠ Filter 'Mobile Phone Primary Camera Resolution' not found. Skipping test.",
		                ThreadContext.get("testName"));
			    return;
			}
		
		
			
			List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listMobilePhonePrimaryCameraResolutionOptionsBy, "mobilephoneprimarycameraresolution");

		    SoftAssert softAssert = new SoftAssert();

		    for (Map<String, Object> product : results) {
		        String filter = ((String) product.get("filter")).toLowerCase();
		        String title = ((String) product.get("title")).toLowerCase();
		        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
		        String about = ((String) product.get("about")).toLowerCase();
		        String techDetails = ((String) product.get("techDetails")).toLowerCase();

		        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
		                        || about.contains(filter) || techDetails.contains(filter);

		        if (!isMatch) {
		        	 log.error("[{}] ❌ Camera Resolution filter '{}' not matched in product details.\n🔍 Title: {}\n🔍 Key Features: {}\n🔍 About: {}\n🔍 Tech Details: {}",
		                     ThreadContext.get("testName"), filter, title, keyFeatures, about, techDetails);
		             log.info("[{}] -------------------------------------------------------------", ThreadContext.get("testName"));
		            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
		                          + "Title: " + title + "\n"
		                          + "Key Features: " + keyFeatures + "\n"
		                          + "About: " + about + "\n"
		                          + "Tech Details: " + techDetails + "\n");
		            System.out.println("---------------------------------------------------------------");
		        } else {
		            log.info("[{}] ✔ Filter '{}' matched in product details.", ThreadContext.get("testName"), filter);

		            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
		        }
		    }
		    
		    log.info("[{}] ⏹ Final assertion for Camera Resolution filter validation...", ThreadContext.get("testName"));


		    softAssert.assertAll(); 
	}

	
//	@Test(priority=13)
	@Test(priority=13, retryAnalyzer = RetryFailedTest.class)
	public void verifyingDiscountFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		ProductListingPage productPage=new ProductListingPage();
		productPage.refreshIfServiceUnavailable();
		GenericUtility genericUtility=new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Discount")) {
	        log.warn("[{}] ⚠ Filter 'Discount' not available. Skipping test.", ThreadContext.get("testName"));

		    System.out.println("Filter option 'Discount' does not exist in the list. Skipping the test.");
		    return ;
		}
		
		
		
		List<Map<String, Object>> results = productPage.applyFilterAndValidateProductsWithResult(productPage.listDiscountOptionsBy,"discount");

	    SoftAssert softAssert = new SoftAssert();

	    for (Map<String, Object> product : results) {
	        String filter = ((String) product.get("filter")).toLowerCase();
	        String title = ((String) product.get("title")).toLowerCase();
	        String keyFeatures = ((String) product.get("keyFeatures")).toLowerCase();
	        String about = ((String) product.get("about")).toLowerCase();
	        String techDetails = ((String) product.get("techDetails")).toLowerCase();

	        boolean isMatch = title.contains(filter) || keyFeatures.contains(filter)
	                        || about.contains(filter) || techDetails.contains(filter);

	        if (!isMatch) {
	        	log.error("[{}] ❌ Discount filter '{}' not found in product.", ThreadContext.get("testName"), filter);
	            log.debug("[{}] 🔍 Title: {}\n🔍 Key Features: {}\n🔍 About: {}\n🔍 Tech Details: {}",
	                    ThreadContext.get("testName"), title, keyFeatures, about, techDetails);
	            log.info("[{}] -------------------------------------------------------------", ThreadContext.get("testName"));

	            softAssert.fail("❌ Brand filter '" + filter + "' not found in product details:\n"
	                          + "Title: " + title + "\n"
	                          + "Key Features: " + keyFeatures + "\n"
	                          + "About: " + about + "\n"
	                          + "Tech Details: " + techDetails + "\n");
	            System.out.println("---------------------------------------------------------------");
	        } else {
	            log.info("[{}] ✔ Filter '{}' matched in at least one product section.", ThreadContext.get("testName"), filter);

	            System.out.println("✔ Filter '" + filter + "' matched in at least one section of product details.");
	        }
	    }

	    log.info("[{}] ⏹ Final assertion for Discount filter validation...", ThreadContext.get("testName"));
	    softAssert.assertAll(); 
	}
}
