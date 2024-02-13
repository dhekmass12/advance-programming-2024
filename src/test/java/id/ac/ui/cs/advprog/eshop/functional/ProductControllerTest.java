package id.ac.ui.cs.advprog.eshop.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProductPageTest() throws Exception{
        ResultActions response = mockMvc.perform(//
                get("/product/create")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void editProductPageTest() throws Exception{
        ResultActions response = mockMvc.perform(//
                get("/product/edit")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void deleteProductPageTest() throws Exception{
        ResultActions response = mockMvc.perform(//
                get("/product/delete")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void listProductPageTest() throws Exception{
        ResultActions response = mockMvc.perform(//
                get("/product/list")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void createProductPostTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Product product = new Product();
        product.setProductId("product id");
        product.setProductName("product name");
        product.setProductQuantity(100);

        mockMvc.perform(post("/product/create")
                        .param("productId", product.getProductId())
                        .param("productName", product.getProductName())
                        .param("productQuantity", Integer.toString(product.getProductQuantity())))
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    public void editProductPostTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Product product = new Product();
        product.setProductId("product id");
        product.setProductName("product name");
        product.setProductQuantity(100);

        mockMvc.perform(
                post("/product/edit")
                        .param("productId", product.getProductId())
                        .param("productName", product.getProductName())
                        .param("productQuantity", Integer.toString(product.getProductQuantity())))
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    public void deleteProductPostTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Product product = new Product();
        product.setProductId("product id");
        product.setProductName("product name");
        product.setProductQuantity(100);

        mockMvc.perform(post("/product/delete")
                        .param("productId", product.getProductId())
                        .param("productName", product.getProductName())
                        .param("productQuantity", Integer.toString(product.getProductQuantity())))
                .andExpect(status().is(302))
                .andReturn();
    }
}