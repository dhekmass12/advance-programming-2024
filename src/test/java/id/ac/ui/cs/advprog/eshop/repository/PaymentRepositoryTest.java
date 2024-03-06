package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Order> orders;
    Map<String, String> voucherCodePaymentData;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("13652556-012a-4c07-b546-54eb1396d79b");
        product.setProductName("Chair");
        product.setProductQuantity(100);
        products.add(product);
        paymentRepository = new PaymentRepository();

        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP12345678ABC");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("1",
                "voucherCode",
                this.voucherCodePaymentData
        );
        payments.add(payment1);
        Payment payment2 = new Payment("2",
                "voucherCode",
                this.voucherCodePaymentData
        );
        payments.add(payment2);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products,
                1708560000L,
                "Safira Sudrajat"
        );
        orders.add(order1);
    }

    @Test
    void testSaveCreate(){
        Order order = orders.get(0);
        Payment payment = payments.get(0);
        Payment result = paymentRepository.addPayment(order, payment.getMethod(), payment.getPaymentData());

        Payment findResult = paymentRepository.getPayment(payments.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertTrue(paymentRepository.getAllOrders().containsKey(payment.getId()));
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments){
            paymentRepository.addPayment(orders.getFirst(), payment.getMethod(), payment.getPaymentData());
        }

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment : payments){
            paymentRepository.addPayment(orders.getFirst(), payment.getMethod(), payment.getPaymentData());
        }

        Payment findResult = paymentRepository.getPayment("GOD PLS HELP ME");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments(){
        for (Payment payment : payments){
            paymentRepository.addPayment(orders.getFirst(), payment.getMethod(), payment.getPaymentData());
        }

        List<Payment> findResult = paymentRepository.getAll();

        assertEquals(payments.size(), findResult.size());

        for (int i = 0; i < findResult.size(); ++i){
            assertEquals(payments.get(i).getId(), findResult.get(i).getId());
            assertEquals(payments.get(i).getMethod(), findResult.get(i).getMethod());
            assertEquals(payments.get(i).getStatus(), findResult.get(i).getStatus());
            assertSame(payments.get(i).getPaymentData(), findResult.get(i).getPaymentData());
        }
    }
}
