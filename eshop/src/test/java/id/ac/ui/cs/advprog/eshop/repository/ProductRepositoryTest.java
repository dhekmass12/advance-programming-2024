package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){

    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById(){
        Product product1 = new Product();
        product1.setProductId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product = productRepository.findProductById("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(product);
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct(){
        Product product1 = new Product();
        product1.setProductId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product oldProduct = productRepository.findProductById("eb55Be9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(oldProduct.getProductId(), "eb55Be9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(oldProduct.getProductName(), "Sampo Cap Bambang");
        assertEquals(oldProduct.getProductQuantity(), 100);

        Product product2 = new Product();
        product2.setProductId(product1.getProductId());
        product2.setProductName("Sampo Head & Shoulders");
        product2.setProductQuantity(10000);
        productRepository.edit(product2);

        Product newProduct = productRepository.findProductById("eb55Be9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(newProduct.getProductId(), "eb55Be9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(newProduct.getProductName(), "Sampo Head & Shoulders");
        assertEquals(newProduct.getProductQuantity(), 10000);
    }

    @Test
    void testDeleteProduct(){
        Product product1 = new Product();
        product1.setProductId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product oldProduct = productRepository.findProductById("eb55Be9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(oldProduct.getProductId(), "eb55Be9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(oldProduct.getProductName(), "Sampo Cap Bambang");
        assertEquals(oldProduct.getProductQuantity(), 100);

        productRepository.delete(product1);

        Product newProduct = productRepository.findProductById("eb55Be9f-1c39-460e-8860-71af6af63bd6");

        assertNull(newProduct);
    }
}
