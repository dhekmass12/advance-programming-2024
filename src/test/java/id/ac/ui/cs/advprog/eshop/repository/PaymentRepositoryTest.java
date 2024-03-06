package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
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
    Map<String, String> voucherCodePaymentData;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP12345678ABC");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "voucherCode",
                this.voucherCodePaymentData
        );
        payments.add(payment1);
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                "voucherCode",
                this.voucherCodePaymentData
        );
        payments.add(payment2);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments){
            paymentRepository.save(payment);
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
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.getPayment("GOD PLS HELP ME");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments(){
        for (Payment payment : payments){
            paymentRepository.save(payment);
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
