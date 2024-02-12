package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.model.Product;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String listProductUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        listProductUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void productWhichHasBeenCreatedIsShownInProductList(ChromeDriver driver) throws Exception{
        Product product = new Product();
        product.setProductName("Biskuit Khong Guan");
        product.setProductQuantity(100);

        // Excercise
        driver.get(baseUrl);
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys(product.getProductName());
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(product.getProductQuantity()));
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        driver.get(listProductUrl);
        WebElement productName = driver.findElement(By.id(product.getProductName()));
        assertEquals(productName.getText(), product.getProductName());
        WebElement productQuantity = driver.findElement(By.id(Integer.toString(product.getProductQuantity())));
        assertEquals(productQuantity.getText(), Integer.toString(product.getProductQuantity()));
    }
}
