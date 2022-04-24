package BBC;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BBC {
    private WebDriver driver;
    private WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger("Test");

    @BeforeEach
    @DisplayName("Устанавливаем драйвер")
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\НовыйЯ\\IdeaProjects\\PatternLesson10_11\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Тест-кейс проверки почты BBC")
    public void firstTest() {
        driver.get("https://www.bbc.com/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='idcta-username']"))).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"username-label\"]/span"), "Электронная почта или имя пользователя"));
        driver.findElement(By.id("user-identifier-input")).sendKeys("turlotv@mail.ru");
        driver.findElement(By.id("password-input")).sendKeys("TTV161090");
        driver.findElement(By.id("submit-button")).click();
        String str = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.bbc.com/", str);
        String s = driver.findElement(By.id("idcta-username")).getText();
        Assertions.assertEquals("Your account", s);
        logger.info("Тест выполнен успешно!");
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}