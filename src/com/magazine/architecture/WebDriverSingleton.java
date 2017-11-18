package com.magazine.architecture;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverSingleton {

	private static WebDriver driver;

	private static int navegador = 2;// 0=firefox;1=ghost;2=Chrome,3=IE

	private static boolean remoto = false;

	private static String URL_HUB = "http://localhost:4444/wd/hub";

	public static WebDriver get() {
		if (driver == null) {
			DesiredCapabilities dc = null;
			switch (navegador) {
			case 0:
				driver = new FirefoxDriver();
				break;
			case 1:
				dc = DesiredCapabilities.phantomjs();
				dc.setJavascriptEnabled(true);
				dc.setCapability("takesScreenshot", true);
				dc.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "drivers/phantomjs.exe");
				if (!remoto) {
					driver = new PhantomJSDriver(dc);
				}
				break;
			case 2:
				dc = DesiredCapabilities.chrome();
				dc.setJavascriptEnabled(true);
				dc.setCapability("chrome.binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
				if (!remoto) {
					driver = new ChromeDriver(dc);
				}
				break;
			case 3:
				dc = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				if (!remoto) {
					driver = new InternetExplorerDriver(dc);
				}
				break;
			default:
				throw new RuntimeException("Navegador \"" + navegador + "\" desconhecido");
			}

			if (remoto) {
				try {
					driver = new RemoteWebDriver(new URL(URL_HUB), dc);
				} catch (MalformedURLException e) {
					throw new RuntimeException("URL do hub inv�lida!");
				}
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return driver;
	}

	public static void capturaEvidencia(String nomeEvidencia) {
		try {
			File evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(evidencia, new File("evidences/" + nomeEvidencia + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void reiniciarOperacao() {
		driver.quit();
		driver = null;
		WebDriverSingleton.get();
	}

}
