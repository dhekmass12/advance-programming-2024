package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    Map<String, String> voucherCodePaymentData;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products,
                1708560000L,
                "Safira Sudrajat"
        );
        orders.add(order1);

        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP12345678ABC");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("1",
                "voucherCode",
                this.voucherCodePaymentData
        );
        payments.add(payment1);
    }

    @Test
    void testCreatePayment(){
        Order order = orders.getFirst();
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).addPayment(order, payment.getMethod(), voucherCodePaymentData);
        paymentService.addPayment(order, payment.getMethod(), voucherCodePaymentData);
        verify(paymentRepository, times(1)).addPayment(order, payment.getMethod(), voucherCodePaymentData);
    }

    @Test
    void testSetStatus(){
        Order order = orders.getFirst();
        Payment payment = payments.getFirst();
        String status = "SUCCESS";
        doReturn(payment).when(paymentRepository).addPayment(order, payment.getMethod(), payment.getPaymentData());
        payment = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).setStatus(payment, status);
        payment = paymentService.setStatus(payment, status);
        verify(paymentRepository, times(1)).setStatus(payment, status);
    }

    @Test
    void testGetPaymentByIdIfIdFound(){
        Order order = orders.getFirst();
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).addPayment(order, payment.getMethod(), payment.getPaymentData());
        paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).getPayment("1");
        Payment result = paymentService.getPayment("1");
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentByIdIfIdNotFound(){
        doReturn(null).when(paymentRepository).getPayment("GOD PLEASE HELP ME");
        assertNull(paymentService.getPayment("GOD PLEASE HELP ME"));
    }

    @Test
    void testGetAllPayments(){
        Order order = orders.getFirst();
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).addPayment(order, payment.getMethod(), payment.getPaymentData());
        paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        List<Payment> findResult = payments;
        doReturn(findResult).when(paymentRepository).getAll();
        paymentService.getAll();
        verify(paymentRepository, times(1)).getAll();
    }
}
