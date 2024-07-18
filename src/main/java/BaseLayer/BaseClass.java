package BaseLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;

	public BaseClass() {
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\java\\ConfigurationLayer\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(f);

			prop = new Properties();

			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void initialisation(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver", "chromedriverpath");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("incognito")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--incognito");
			driver = new ChromeDriver(opt);
		}

		else if (browserName.equalsIgnoreCase("headless")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--headless");
			driver = new ChromeDriver(opt);
		} else {
			System.out.println("Please Provide a Valid BrowserName");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		String url = prop.getProperty("URL");
		driver.get(url);

	}

}
