package amazonfilterapplicatione2e;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import amazonfilterapplicatione2e.reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class ScreenshotUtil {

    private static final String BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/latest";
	private  final static Logger log = LoggerUtility.getLogger(ScreenshotUtil.class);

    static {
        File baseDir = new File(BASE_DIR);
        if (baseDir.exists()) {
            try {
                FileUtils.deleteDirectory(baseDir);  // Delete previous run's screenshots
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        baseDir.mkdirs();  // Recreate
    }
    
    
    /**
     * Capture screenshot with default test name (from ThreadContext) + timestamp.
     */
    public static String capture() {
        String testName = ThreadContext.get("testName");
        return captureInternal(testName, null);
    }

    /**
     * Capture screenshot with a custom file name (optional context).
     */
    public static String capture(String customName) {
        String testName = ThreadContext.get("testName");
        return captureInternal(testName, customName);
    }

    
    public static String capture(String testName, String filterValue, int productIndex) {
        String customName = "Filter->" + filterValue + "_Index_" + productIndex;
        return captureInternal(testName, customName);
    }
    
    
    public static String capture(String testName, String filterValue) {
        String customName = "Filter Applied is " + filterValue;
        return captureInternal(testName, customName);
    }
    
    public static String capture(String testName, String filterValue,String customMessage) {
        String customName = customMessage+"  "+"Filter Applied is " + filterValue;
        return captureInternal(testName, customName);
    }
    

    
    
    private static String captureInternal(String testName, String customName) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String folderPath = "test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
        new File(folderPath).mkdirs();

        String fileName = (customName != null && !customName.isEmpty())
                ? customName + "__" + timestamp + ".png"
                : testName + "__" + timestamp + ".png";

        String fullPath = folderPath + "/" + fileName;
        WebDriver driver = DriverManager.getDriver();

        log.info("[{}] Capturing screenshot: {}", ThreadContext.get("testName"), fileName);

        try {
            try {
                driver.getTitle(); // check if driver is alive
            } catch (Exception e) {
                log.warn("[{}] WebDriver seems unresponsive. Skipping screenshot.", ThreadContext.get("testName"));
                return null;
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));

            log.info("[{}] Screenshot saved at: {}", ThreadContext.get("testName"), fullPath);
        } catch (WebDriverException we) {
            log.error("[{}] WebDriverException during screenshot: {}", ThreadContext.get("testName"), we.getMessage());
            return null;
        } catch (IOException ioe) {
            log.error("[{}] IOException while saving screenshot: {}", ThreadContext.get("testName"), ioe.getMessage());
            return null;
        } catch (Exception e) {
            log.error("[{}] Unexpected exception while capturing screenshot: {}", ThreadContext.get("testName"), e.getMessage());
            return null;
        }

        String relativePath = "../screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName + "/" + fileName;
        return relativePath.replace("\\", "/");
    }
    

    


    

}
