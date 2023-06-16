package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

    public class BasicDriver extends MyMethods{
        private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
        private static ThreadLocal<String> threadDriverName = new ThreadLocal<>();

        public static WebDriver getDriver() {
            if (threadDriver.get() == null) {

                if (threadDriverName.get() == null) {
                    threadDriverName.set("chrome");
                }

                switch (threadDriverName.get()) {
                    case "firefox":
                        threadDriver.set(new FirefoxDriver());
                        threadDriver.get().manage().window().maximize();
                        break;
                    case "safari":
                        threadDriver.set(new SafariDriver());
                        threadDriver.get().manage().window().maximize();
                        break;
                    case "edge":
                        threadDriver.set(new EdgeDriver());
                        threadDriver.get().manage().window().maximize();
                        break;
                    default:
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--remote-allow-origins=*"); // To solve the error with Chrome v111
                        threadDriver.set(new ChromeDriver(options));
                        threadDriver.get().manage().window().maximize();
                }
            }
            return threadDriver.get();
        }


    }


