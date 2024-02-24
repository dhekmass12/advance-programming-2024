package id.ac.ui.cs.advprog.eshop;

import id.ac.ui.cs.advprog.eshop.controller.CarController;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTests {
    @Autowired
    private ProductController productController;
    @Autowired
    private CarController carController;

    @Test
    void contextLoads() throws Exception{
        assertThat(productController).isNotNull();
        assertThat(carController).isNotNull();
    }

    @Test
    void main() {
        EshopApplication.main(new String[] {});
    }
}
