import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//TODO add code to import webdriver for given web browser
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumEseoTest {
        private static WebDriver webdriver;

        private static final String TITLE_ENGLISH = "Engineering studies in France-electronics and computer engineering school | ESEO";
        private static final String TITLE_FRENCH = "ESEO - Grande Ecole d'Ingénieurs Généralistes à Angers, Paris, Dijon";
        @BeforeAll
        public static void beforeTests(){
        //TODO add code here to initialise the webdriver
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                SeleniumEseoTest.webdriver = new ChromeDriver(options);
        }

        @Test
        @Order(1)
        public void testHomePage () {
        SeleniumEseoTest.webdriver.get("https://eseo.fr/en/");
        Assertions.assertEquals(SeleniumEseoTest.TITLE_ENGLISH,
                SeleniumEseoTest.webdriver.getTitle(), "Web page title");
        try{
                File screenshot = ((TakesScreenshot)
                        SeleniumEseoTest.webdriver).getScreenshotAs(OutputType.FILE);
                ImageIO.write(ImageIO.read(screenshot),"PNG",new File("screenshot.png"));
        }catch(IOException ioe) {
                Assertions.fail("Could not save screenshot.",ioe);
        }

        }


        @AfterAll
        public static void afterTests(){

                SeleniumEseoTest.webdriver.close();
        }
}
